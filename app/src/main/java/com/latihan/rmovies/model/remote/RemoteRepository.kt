package com.latihan.rmovies.model.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.rmovies.BuildConfig
import com.latihan.rmovies.model.entity.Item
import com.latihan.rmovies.model.entity.TvShowDetails
import com.latihan.rmovies.network.ApiConfig
import com.latihan.rmovies.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    private val apiConfig = ApiConfig
    private var handler = Handler(Looper.getMainLooper())

    fun getMovies(): LiveData<List<Item>> {
        val listMovies = MutableLiveData<List<Item>>()

        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.create().getMovies(apiKey).enqueue(object : Callback<ItemResponse> {
                override fun onResponse(
                    call: Call<ItemResponse>,
                    response: Response<ItemResponse>
                ) {
                    listMovies.value = response.body()?.list
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {

                }

            })

        }, delay)
        return listMovies
    }

    fun getDetailMovie(moviesId: String): LiveData<Item> {
        val detailMovie = MutableLiveData<Item>()

        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.create().getDetailsMovie(moviesId, apiKey).enqueue(object : Callback<Item> {
                override fun onResponse(call: Call<Item>, response: Response<Item>) {
                    detailMovie.value = response.body()
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<Item>, t: Throwable) {

                }
            })
        }, delay)

        return detailMovie
    }

    fun getTvShows(): LiveData<List<Item>> {
        val listShows = MutableLiveData<List<Item>>()

        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.create().getTvShows(apiKey).enqueue(object : Callback<ItemResponse> {
                override fun onResponse(
                    call: Call<ItemResponse>,
                    response: Response<ItemResponse>
                ) {
                    listShows.value = response.body()?.list
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {

                }

            })
        }, delay)

        return listShows
    }

    fun getDetailShow(showsId: String): LiveData<TvShowDetails> {
        val detailShow = MutableLiveData<TvShowDetails>()

        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.create().getTvShowDetails(showsId, apiKey)
                .enqueue(object : Callback<TvShowDetails> {
                    override fun onResponse(
                        call: Call<TvShowDetails>,
                        response: Response<TvShowDetails>
                    ) {
                        detailShow.value = response.body()
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<TvShowDetails>, t: Throwable) {

                    }
                })
        }, delay)

        return detailShow
    }


    companion object {
        const val apiKey = BuildConfig.API_KEY
        private var instance: RemoteRepository? = null
        private const val delay: Long = 1500

        fun getInstance(): RemoteRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteRepository()
            }
    }

}
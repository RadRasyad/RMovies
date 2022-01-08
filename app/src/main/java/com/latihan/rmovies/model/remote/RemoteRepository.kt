package com.latihan.rmovies.model.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.rmovies.BuildConfig
import com.latihan.rmovies.model.entity.Item
import com.latihan.rmovies.model.entity.TvShowDetails
import com.latihan.rmovies.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    private val apiConfig = ApiConfig

    fun getMovies(): LiveData<List<Item>> {
        val listMovies = MutableLiveData<List<Item>>()

        apiConfig.create().getMovies(apiKey).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(
                call: Call<ItemResponse>,
                response: Response<ItemResponse>
            ) {
                listMovies.value = response.body()?.list
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {

            }

        })
        return listMovies
    }

    fun getDetailMovie(moviesId: String): LiveData<Item> {
        val detailMovie = MutableLiveData<Item>()
        apiConfig.create().getDetailsMovie(moviesId, apiKey).enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                detailMovie.value = response.body()
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {

            }
        })

        return detailMovie
    }

    fun getTvShows(): LiveData<List<Item>> {
        val listShows = MutableLiveData<List<Item>>()

        apiConfig.create().getTvShows(apiKey).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(
                call: Call<ItemResponse>,
                response: Response<ItemResponse>
            ) {
                listShows.value = response.body()?.list
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {

            }

        })
        return listShows
    }

    fun getDetailShow(showsId: String): LiveData<TvShowDetails> {
        val detailShow = MutableLiveData<TvShowDetails>()
        apiConfig.create().getTvShowDetails(showsId, apiKey).enqueue(object : Callback<TvShowDetails> {
            override fun onResponse(call: Call<TvShowDetails>, response: Response<TvShowDetails>) {
                detailShow.value = response.body()
            }

            override fun onFailure(call: Call<TvShowDetails>, t: Throwable) {

            }
        })

        return detailShow
    }


    companion object {
        const val apiKey = BuildConfig.API_KEY
        private var instance: RemoteRepository? = null

        fun getInstance(): RemoteRepository =
            instance?: synchronized(this) {
                instance ?: RemoteRepository()
            }
    }

}
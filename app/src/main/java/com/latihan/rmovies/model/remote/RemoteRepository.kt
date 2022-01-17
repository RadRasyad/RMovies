package com.latihan.rmovies.model.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.rmovies.BuildConfig
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.remote.response.MoviesResponse
import com.latihan.rmovies.model.remote.response.TvShowsResponse
import com.latihan.rmovies.network.ApiConfig
import com.latihan.rmovies.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    private val apiConfig = ApiConfig

    fun getMovies(): LiveData<ApiResponse<List<MoviesEntity>>> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<ApiResponse<List<MoviesEntity>>>()
        apiConfig.create().getMovies(apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val result = response.body()!!.list
                if (result!=null) {
                    Log.d("remoterepo :", result.size.toString())
                    movies.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return movies
    }

    interface GetDetailMovieCallback {
        fun onResponse(detailMovieResponse: MoviesEntity)
    }

    fun getDetailMovie(moviesId: String, callback: GetDetailMovieCallback) {
        EspressoIdlingResource.increment()
        apiConfig.create().getDetailsMovie(moviesId, apiKey).enqueue(object : Callback<MoviesEntity> {
            override fun onResponse(call: Call<MoviesEntity>, response: Response<MoviesEntity>) {
                response.body()?.let { callback.onResponse(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesEntity>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShows(): MutableLiveData<ApiResponse<List<TvShowsEntity>>> {
        EspressoIdlingResource.increment()
        val shows = MutableLiveData<ApiResponse<List<TvShowsEntity>>>()
        apiConfig.create().getTvShows(apiKey).enqueue(object : Callback<TvShowsResponse> {
            override fun onResponse(
                call: Call<TvShowsResponse>,
                response: Response<TvShowsResponse>
            ) {
                val result = response.body()?.list
                if (result!=null) {
                    shows.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })

        return shows
    }

    interface GetDetailShowCallback {
        fun onResponse(detailShowResponse: TvShowsEntity)
    }

    fun getDetailShow(showsId: String, callback: GetDetailShowCallback) {

        EspressoIdlingResource.increment()
        apiConfig.create().getTvShowDetails(showsId, apiKey)
            .enqueue(object : Callback<TvShowsEntity> {
                override fun onResponse(
                    call: Call<TvShowsEntity>,
                    response: Response<TvShowsEntity>
                ) {
                    response.body()?.let { callback.onResponse(it) }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TvShowsEntity>, t: Throwable) {
                    EspressoIdlingResource.decrement()
                }
            })

    }


    companion object {
        const val apiKey = BuildConfig.API_KEY
        private var instance: RemoteRepository? = null

        fun getInstance(): RemoteRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteRepository()
            }
    }

}
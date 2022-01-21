package com.latihan.rmovies.model.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.rmovies.BuildConfig
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.remote.response.Movies
import com.latihan.rmovies.model.remote.response.MoviesResponse
import com.latihan.rmovies.model.remote.response.TvShows
import com.latihan.rmovies.model.remote.response.TvShowsResponse
import com.latihan.rmovies.network.ApiConfig
import com.latihan.rmovies.utils.EspressoIdlingResource
import com.latihan.rmovies.vo.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    private val apiConfig = ApiConfig

    fun getMovies(): LiveData<ApiResponse<List<Movies>>> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<ApiResponse<List<Movies>>>()
        apiConfig.create().getMovies(apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val result = response.body()?.list
                if (result != null) {
                    val add = ApiResponse.success(result)
                    movies.postValue(add)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return movies

    }

    fun getDetailMovie(moviesId: String): LiveData<ApiResponse<List<Movies>>> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<ApiResponse<List<Movies>>>()
        apiConfig.create().getDetailsMovie(moviesId, apiKey).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val result = response.body()?.list
                if (result != null) {
                    val add = ApiResponse.success(result)
                    movies.postValue(add)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return movies
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShows>>> {
        EspressoIdlingResource.increment()
        val shows = MutableLiveData<ApiResponse<List<TvShows>>>()
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

    fun getDetailShow(showsId: String): LiveData<ApiResponse<List<TvShows>>> {
        EspressoIdlingResource.increment()
        val shows = MutableLiveData<ApiResponse<List<TvShows>>>()
        apiConfig.create().getTvShowDetails(showsId, apiKey)
            .enqueue(object : Callback<TvShowsResponse> {
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

    companion object {
        const val apiKey = BuildConfig.API_KEY
        private var instance: RemoteRepository? = null

        fun getInstance(): RemoteRepository =
            instance ?: synchronized(this) {
                instance ?: RemoteRepository()
            }
    }

}
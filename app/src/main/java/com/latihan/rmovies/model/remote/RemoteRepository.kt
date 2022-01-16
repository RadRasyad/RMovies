package com.latihan.rmovies.model.remote

import com.latihan.rmovies.BuildConfig
import com.latihan.rmovies.model.local.entity.Item
import com.latihan.rmovies.model.local.entity.TvShowDetails
import com.latihan.rmovies.network.ApiConfig
import com.latihan.rmovies.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    private val apiConfig = ApiConfig

    interface GetMoviesCallback {
        fun onResponse(movies: List<Item>)
    }

    interface GetDetailMovieCallback {
        fun onResponse(detailMovieResponse: Item)
    }

    interface GetTvShowsCallback {
        fun onResponse(shows: List<Item>)
    }

    interface GetDetailShowCallback {
        fun onResponse(detailShowResponse: TvShowDetails)
    }

    fun getMovies(callback: GetMoviesCallback) {
        EspressoIdlingResource.increment()
        apiConfig.create().getMovies(apiKey).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(
                call: Call<ItemResponse>,
                response: Response<ItemResponse>
            ) {
                response.body()?.list?.let { callback.onResponse(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailMovie(moviesId: String, callback: GetDetailMovieCallback) {
        EspressoIdlingResource.increment()
        apiConfig.create().getDetailsMovie(moviesId, apiKey).enqueue(object : Callback<Item> {
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                response.body()?.let { callback.onResponse(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShows(callback: GetTvShowsCallback) {
        EspressoIdlingResource.increment()
        apiConfig.create().getTvShows(apiKey).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(
                call: Call<ItemResponse>,
                response: Response<ItemResponse>
            ) {
                response.body()?.list?.let { callback.onResponse(it) }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailShow(showsId: String, callback: GetDetailShowCallback) {

        EspressoIdlingResource.increment()
        apiConfig.create().getTvShowDetails(showsId, apiKey)
            .enqueue(object : Callback<TvShowDetails> {
                override fun onResponse(
                    call: Call<TvShowDetails>,
                    response: Response<TvShowDetails>
                ) {
                    response.body()?.let { callback.onResponse(it) }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<TvShowDetails>, t: Throwable) {
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
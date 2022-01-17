package com.latihan.rmovies.network

import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.remote.response.MoviesResponse
import com.latihan.rmovies.model.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getDetailsMovie(@Path("movie_id") movieId: String?,
                        @Query("api_key") apiKey: String?
    ) : Call<MoviesEntity>

    @GET("tv/popular")
    fun getTvShows(@Query("api_key") apiKey: String?) : Call<TvShowsResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetails(@Path("tv_id") tvId: String?,
                         @Query("api_key") apiKey: String?
    ) : Call<TvShowsEntity>
}
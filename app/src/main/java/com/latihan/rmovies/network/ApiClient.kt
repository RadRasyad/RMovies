package com.latihan.rmovies.network

import com.latihan.rmovies.model.entity.Item
import com.latihan.rmovies.model.entity.TvShowDetails
import com.latihan.rmovies.model.remote.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String): Call<ItemResponse>

    @GET("movie/{movie_id}")
    fun getDetailsMovie(@Path("movie_id") movieId: String?,
                        @Query("api_key") apiKey: String?
    ) : Call<Item>

    @GET("tv/popular")
    fun getTvShows(@Query("api_key") apiKey: String?) : Call<ItemResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetails(@Path("tv_id") tvId: String?,
                         @Query("api_key") apiKey: String?
    ) : Call<TvShowDetails>
}
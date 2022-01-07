package com.latihan.rmovies.network

import com.latihan.rmovies.model.remote.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("movie/popular")
    fun getMovies(@Query("api_key") apiKey: String): Call<ItemResponse>

    @GET("tv/popular")
    fun getTvShows(@Query("api_key") apiKey: String?) : Call<ItemResponse>

}
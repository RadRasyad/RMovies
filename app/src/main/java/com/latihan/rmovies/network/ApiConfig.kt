package com.latihan.rmovies.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    fun create(): ApiClient {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiClient::class.java)
    }
}
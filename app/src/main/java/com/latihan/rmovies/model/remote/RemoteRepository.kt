package com.latihan.rmovies.model.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.rmovies.BuildConfig
import com.latihan.rmovies.model.entity.Item
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
                Log.d("apiResponse", listMovies.value?.size.toString())
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {

            }

        })
        return listMovies
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
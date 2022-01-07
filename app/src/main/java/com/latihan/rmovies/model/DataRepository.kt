package com.latihan.rmovies.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.latihan.rmovies.model.entity.Item
import com.latihan.rmovies.model.remote.DataSource
import com.latihan.rmovies.model.remote.RemoteRepository

class DataRepository(private val remoteRepository: RemoteRepository): DataSource {

    companion object {
        private var instance: DataRepository? = null

        fun getInstance(remoteRepository: RemoteRepository): DataRepository {

            synchronized(DataRepository::class.java) {
                if (instance == null) {
                    instance = DataRepository(remoteRepository)
                }
            }
            return instance!!
        }
    }

    override fun getMovies() = remoteRepository.getMovies()

    override fun getTvShows(): LiveData<List<Item>> {
        TODO("Not yet implemented")
    }


}
package com.latihan.rmovies.di

import android.content.Context
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.remote.RemoteRepository

object Injection {
    fun moviesRepository(context: Context): DataRepository{

        val remoteRepository = RemoteRepository.getInstance()
        return DataRepository.getInstance(remoteRepository)
    }

}
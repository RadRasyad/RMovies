package com.latihan.rmovies.di

import android.content.Context
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.LocalDataSource
import com.latihan.rmovies.model.local.room.CDatabase
import com.latihan.rmovies.model.remote.RemoteRepository
import com.latihan.rmovies.utils.AppExecutors

object Injection {
    fun moviesRepository(context: Context): DataRepository{

        val db = CDatabase.getInstance(context)

        val remoteRepository = RemoteRepository.getInstance()
        val localDataSource = LocalDataSource.getInstance(db.dao())
        val appExecutors = AppExecutors()
        return DataRepository.getInstance(remoteRepository, localDataSource, appExecutors)
    }

}
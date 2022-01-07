package com.latihan.rmovies.model.remote

import androidx.lifecycle.LiveData
import com.latihan.rmovies.model.entity.Item

interface DataSource {
    fun getMovies(): LiveData<List<Item>>
    fun getTvShows(): LiveData<List<Item>>
}
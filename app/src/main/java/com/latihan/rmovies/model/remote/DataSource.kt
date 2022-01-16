package com.latihan.rmovies.model.remote

import androidx.lifecycle.LiveData
import com.latihan.rmovies.model.local.entity.Item
import com.latihan.rmovies.model.local.entity.TvShowDetails

interface DataSource {

    fun getMovies(): LiveData<List<Item>>
    fun getTvShows(): LiveData<List<Item>>
    fun getMovieDetail(movieId: String): LiveData<Item>
    fun getTvShowDetail(tvShowId: String): LiveData<TvShowDetails>
}
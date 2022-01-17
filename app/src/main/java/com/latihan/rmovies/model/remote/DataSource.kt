package com.latihan.rmovies.model.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.vo.Resource

interface DataSource {

    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>>
    fun getMovieDetail(movieId: String): LiveData<MoviesEntity>

    fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>>
    fun getTvShowDetail(tvShowId: String): LiveData<TvShowsEntity>
}
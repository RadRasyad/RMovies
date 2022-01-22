package com.latihan.rmovies.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity

class FavoriteViewModel(private val dataRepository: DataRepository): ViewModel() {

    fun getFavMovies(sort: String): LiveData<PagedList<MoviesEntity>> = dataRepository.getFavMovies(sort)

    fun getFavShow(): LiveData<PagedList<TvShowsEntity>> = dataRepository.getFavShows()
}
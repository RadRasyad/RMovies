package com.latihan.rmovies.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.MoviesEntity

class FavoriteViewModel(private val dataRepository: DataRepository): ViewModel() {

    fun getFavMovies(): LiveData<PagedList<MoviesEntity>> = dataRepository.getFavMovies()
}
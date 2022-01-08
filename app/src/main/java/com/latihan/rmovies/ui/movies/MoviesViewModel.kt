package com.latihan.rmovies.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.entity.Item

class MoviesViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val movies: LiveData<List<Item>> = dataRepository.getMovies()

    fun getDetailMovie(movieId: String): LiveData<Item> = dataRepository.getMovieDetail(movieId)

}
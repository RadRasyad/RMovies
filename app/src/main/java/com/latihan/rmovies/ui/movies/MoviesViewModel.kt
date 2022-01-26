package com.latihan.rmovies.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.vo.Resource

class MoviesViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getListMovies(): LiveData<Resource<PagedList<MoviesEntity>>> = dataRepository.getMovies()

    fun getDetailMovie(movieId: String): LiveData<Resource<MoviesEntity>> = dataRepository.getMovieDetail(movieId)

    fun checkFavMovies(id: Int) =
        dataRepository.checkFavMovies(id)

    fun setFavMovies(moviesEntity: MoviesEntity) =
        dataRepository.setFavMovies(moviesEntity)

    fun deleteFavMovies(moviesEntity: MoviesEntity) =
        dataRepository.delFavMovies(moviesEntity)

}
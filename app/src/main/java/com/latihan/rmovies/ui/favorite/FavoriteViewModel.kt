package com.latihan.rmovies.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity

class FavoriteViewModel(private val dataRepository: DataRepository): ViewModel() {

    fun getFavMovies(sort: String): LiveData<PagedList<FavoriteMoviesEntity>> = dataRepository.getFavMovies(sort)

    fun getFavMDetail(id: String): LiveData<FavoriteMoviesEntity> = dataRepository.getFavMDetail(id)

    fun delFavMovie(favoriteMoviesEntity: FavoriteMoviesEntity) = dataRepository.delFavM(favoriteMoviesEntity)

    fun getFavShow(sort: String): LiveData<PagedList<FavoriteTvShowsEntity>> = dataRepository.getFavShows(sort)

    fun getFavSDetail(id: String): LiveData<FavoriteTvShowsEntity> = dataRepository.getFavSDetail(id)

    fun delFavShow(favoriteTvShowsEntity: FavoriteTvShowsEntity) = dataRepository.delFavS(favoriteTvShowsEntity)
}
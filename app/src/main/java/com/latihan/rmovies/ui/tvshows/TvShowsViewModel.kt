package com.latihan.rmovies.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.vo.Resource

class TvShowsViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getListShows(): LiveData<Resource<PagedList<TvShowsEntity>>> = dataRepository.getTvShows()

    fun getDetailShow(showsId: String): LiveData<Resource<TvShowsEntity>> =
        dataRepository.getTvShowDetail(showsId)

    fun setFavShows(showsEntity: TvShowsEntity) {
        val newState = !showsEntity.favoriteShow
        dataRepository.setFavShows(showsEntity, newState)
    }
}
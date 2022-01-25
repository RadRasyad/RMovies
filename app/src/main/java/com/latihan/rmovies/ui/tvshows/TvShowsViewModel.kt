package com.latihan.rmovies.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.vo.Resource

class TvShowsViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getListShows(): LiveData<Resource<PagedList<TvShowsEntity>>> = dataRepository.getTvShows()

    fun getDetailShow(showsId: String): LiveData<Resource<TvShowsEntity>> =
        dataRepository.getTvShowDetail(showsId)

    fun checkFavShow(id: Int) =
        dataRepository.checkFavShow(id)

    fun setFavShow(tvShowsEntity: TvShowsEntity) =
        dataRepository.setFavShow(tvShowsEntity)

    fun deleteFavShow(tvShowsEntity: TvShowsEntity) =
        dataRepository.delFavShow(tvShowsEntity)

}
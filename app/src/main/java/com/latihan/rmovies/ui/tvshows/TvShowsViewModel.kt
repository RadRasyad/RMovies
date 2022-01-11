package com.latihan.rmovies.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.rmovies.model.DataRepository
import com.latihan.rmovies.model.entity.Item
import com.latihan.rmovies.model.entity.TvShowDetails

class TvShowsViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getListShows(): LiveData<List<Item>> = dataRepository.getTvShows()

    fun getDetailShow(showsId: String): LiveData<TvShowDetails> =
        dataRepository.getTvShowDetail(showsId)

}
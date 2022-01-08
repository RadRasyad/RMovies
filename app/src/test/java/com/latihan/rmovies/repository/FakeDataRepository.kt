package com.latihan.rmovies.repository

import androidx.lifecycle.LiveData
import com.latihan.rmovies.model.entity.Item
import com.latihan.rmovies.model.entity.TvShowDetails
import com.latihan.rmovies.model.remote.DataSource
import com.latihan.rmovies.model.remote.RemoteRepository

class FakeDataRepository(private val remoteRepository: RemoteRepository): DataSource {

    override fun getMovies() = remoteRepository.getMovies()

    override fun getTvShows() = remoteRepository.getTvShows()

    override fun getMovieDetail(movieId: String): LiveData<Item> = remoteRepository.getDetailMovie(movieId)

    override fun getTvShowDetail(tvShowId: String): LiveData<TvShowDetails> = remoteRepository.getDetailShow(tvShowId)

}
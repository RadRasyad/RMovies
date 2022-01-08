package com.latihan.rmovies.model

import androidx.lifecycle.LiveData
import com.latihan.rmovies.model.entity.Item
import com.latihan.rmovies.model.entity.TvShowDetails
import com.latihan.rmovies.model.remote.DataSource
import com.latihan.rmovies.model.remote.RemoteRepository

class DataRepository(private val remoteRepository: RemoteRepository): DataSource {

    companion object {
        private var instance: DataRepository? = null

        fun getInstance(remoteRepository: RemoteRepository): DataRepository {

            synchronized(DataRepository::class.java) {
                if (instance == null) {
                    instance = DataRepository(remoteRepository)
                }
            }
            return instance!!
        }
    }

    override fun getMovies() = remoteRepository.getMovies()

    override fun getTvShows() = remoteRepository.getTvShows()

    override fun getMovieDetail(movieId: String): LiveData<Item> = remoteRepository.getDetailMovie(movieId)

    override fun getTvShowDetail(tvShowId: String): LiveData<TvShowDetails> = remoteRepository.getDetailShow(tvShowId)


}
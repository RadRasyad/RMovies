package com.latihan.rmovies.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.rmovies.model.local.entity.Item
import com.latihan.rmovies.model.local.entity.TvShowDetails
import com.latihan.rmovies.model.remote.DataSource
import com.latihan.rmovies.model.remote.RemoteRepository

class DataRepository(private val remoteRepository: RemoteRepository): DataSource {

    override fun getMovies(): LiveData<List<Item>> {
        val movieResults = MutableLiveData<List<Item>>()
        remoteRepository.getMovies(object : RemoteRepository.GetMoviesCallback {
            override fun onResponse(movies: List<Item>) {
                movieResults.postValue(movies)
            }
        })
        return movieResults
    }

    override fun getTvShows(): LiveData<List<Item>> {
        val showsResults = MutableLiveData<List<Item>>()
        remoteRepository.getTvShows(object : RemoteRepository.GetTvShowsCallback {
            override fun onResponse(shows: List<Item>) {
                showsResults.postValue(shows)
            }
        })
        return showsResults
    }

    override fun getMovieDetail(movieId: String): LiveData<Item> {
        val detailMovie = MutableLiveData<Item>()
        remoteRepository.getDetailMovie(movieId, object: RemoteRepository.GetDetailMovieCallback{
            override fun onResponse(detailMovieResponse: Item) {
                detailMovie.postValue(detailMovieResponse)
            }

        })
        return detailMovie
    }

    override fun getTvShowDetail(tvShowId: String): LiveData<TvShowDetails> {
        val detailShow = MutableLiveData<TvShowDetails>()
        remoteRepository.getDetailShow(tvShowId, object: RemoteRepository.GetDetailShowCallback{
            override fun onResponse(detailShowResponse: TvShowDetails) {
                detailShow.postValue(detailShowResponse)
            }

        })
        return detailShow
    }

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

}
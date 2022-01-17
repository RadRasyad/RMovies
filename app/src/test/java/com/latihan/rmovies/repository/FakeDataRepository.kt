package com.latihan.rmovies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.remote.DataSource
import com.latihan.rmovies.model.remote.RemoteRepository

class FakeDataRepository(private val remoteRepository: RemoteRepository): DataSource {

    override fun getMovies(): LiveData<List<MoviesEntity>> {
        val movieResults = MutableLiveData<List<MoviesEntity>>()
        remoteRepository.getMovies(object : RemoteRepository.GetMoviesCallback {
            override fun onResponse(movies: List<MoviesEntity>) {
                movieResults.postValue(movies)
            }
        })
        return movieResults
    }

    override fun getTvShows(): LiveData<List<TvShowsEntity>> {
        val showResults = MutableLiveData<List<TvShowsEntity>>()
        remoteRepository.getTvShows(object : RemoteRepository.GetTvShowsCallback {
            override fun onResponse(shows: List<TvShowsEntity>) {
                showResults.postValue(shows)
            }
        })
        return showResults
    }

    override fun getMovieDetail(movieId: String): LiveData<MoviesEntity> {
        val detailMovie = MutableLiveData<MoviesEntity>()
        remoteRepository.getDetailMovie(movieId, object: RemoteRepository.GetDetailMovieCallback{
            override fun onResponse(detailMovieResponse: MoviesEntity) {
                detailMovie.postValue(detailMovieResponse)
            }

        })
        return detailMovie
    }

    override fun getTvShowDetail(tvShowId: String): LiveData<TvShowsEntity> {
        val detailShow = MutableLiveData<TvShowsEntity>()
        remoteRepository.getDetailShow(tvShowId, object: RemoteRepository.GetDetailShowCallback{
            override fun onResponse(detailShowResponse: TvShowsEntity) {
                detailShow.postValue(detailShowResponse)
            }

        })
        return detailShow
    }

}
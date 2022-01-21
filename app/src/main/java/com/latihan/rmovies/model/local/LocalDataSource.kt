package com.latihan.rmovies.model.local


import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.local.room.CDao

class LocalDataSource private constructor(private val mCDao: CDao) {

    fun getMovies(): DataSource.Factory<Int, MoviesEntity> = mCDao.getMovies()

    fun getMovieDetail(movieId: String): LiveData<MoviesEntity> = mCDao.getMovieDetail(movieId)

    fun insertMovies(moviesEntity: ArrayList<MoviesEntity>) = mCDao.insertMovies(moviesEntity)

    fun updateMovies(moviesEntity: ArrayList<MoviesEntity>) = mCDao.updateMovies(moviesEntity)

    fun getShows(): DataSource.Factory<Int, TvShowsEntity> = mCDao.getTvShows()

    fun getShowDetail(showId: String): LiveData<TvShowsEntity> = mCDao.getTvShowDetail(showId)

    fun insertShows(showEntities: ArrayList<TvShowsEntity>) = mCDao.insertShows(showEntities)

    fun updateShows(showsEntity: TvShowsEntity) = mCDao.updateShows(showsEntity)

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mCDao: CDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(mCDao)
    }

}
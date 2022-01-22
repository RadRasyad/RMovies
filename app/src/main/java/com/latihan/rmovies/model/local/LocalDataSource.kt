package com.latihan.rmovies.model.local


import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.local.room.CDao
import com.latihan.rmovies.utils.SortUtils

class LocalDataSource private constructor(private val mCDao: CDao) {

    fun getMovies(): DataSource.Factory<Int, MoviesEntity> = mCDao.getMovies()

    fun getMovieDetail(movieId: String): LiveData<MoviesEntity> = mCDao.getMovieDetail(movieId)

    fun insertMovies(moviesEntity: ArrayList<MoviesEntity>) = mCDao.insertMovies(moviesEntity)

    fun updateMovies(moviesEntity: MoviesEntity) = mCDao.updateMovies(moviesEntity)

    fun setFavMovies(movies: MoviesEntity, state: Boolean) {
        movies.favoriteMovies = state
        mCDao.updateMovies(movies)
    }

    fun getFavMovies(sort: String): DataSource.Factory<Int, MoviesEntity> {
        val query = SortUtils.getSortedQuery(sort)
        return mCDao.getFavoriteMovies(query)
    }


    fun getShows(): DataSource.Factory<Int, TvShowsEntity> = mCDao.getTvShows()

    fun getShowDetail(showId: String): LiveData<TvShowsEntity> = mCDao.getTvShowDetail(showId)

    fun insertShows(showsEntity: ArrayList<TvShowsEntity>) = mCDao.insertShows(showsEntity)

    fun updateShows(showsEntity: TvShowsEntity) = mCDao.updateShows(showsEntity)

    fun setFavShows(shows: TvShowsEntity, state: Boolean) {
        shows.favoriteShow = state
        mCDao.updateShows(shows)
    }

    fun getFavShows(): DataSource.Factory<Int, TvShowsEntity> = mCDao.getFavoriteTvShow()

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mCDao: CDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(mCDao)
    }

}
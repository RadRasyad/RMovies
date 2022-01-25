package com.latihan.rmovies.model.local


import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity
import com.latihan.rmovies.model.local.room.CDao
import com.latihan.rmovies.utils.SortUtils

class LocalDataSource private constructor(private val mCDao: CDao) {

    fun getMovies(): DataSource.Factory<Int, MoviesEntity> = mCDao.getMovies()

    fun getMovieDetail(movieId: String): LiveData<MoviesEntity> = mCDao.getMovieDetail(movieId)

    fun insertMovies(moviesEntity: ArrayList<MoviesEntity>) = mCDao.insertMovies(moviesEntity)

    fun updateMovies(moviesEntity: MoviesEntity) = mCDao.updateMovies(moviesEntity)

    fun getFavMovies(sort: String): DataSource.Factory<Int, FavoriteMoviesEntity> {
        val query = SortUtils.getSortedMovies(sort)
        return mCDao.getFavMovies(query)
    }

    fun checkFavMovies(id : Int) = mCDao.checkMovies(id)

    fun insertFavMovies(favoriteMovies: FavoriteMoviesEntity) = mCDao.insertFavMovies(favoriteMovies)

    fun deleteFavMovies(favoriteMovies: FavoriteMoviesEntity) = mCDao.deleteFavMovies(favoriteMovies)

    fun getFavMDetail(id: String) = mCDao.getFavMovieDetail(id)


    fun getShows(): DataSource.Factory<Int, TvShowsEntity> = mCDao.getTvShows()

    fun getShowDetail(showId: String): LiveData<TvShowsEntity> = mCDao.getTvShowDetail(showId)

    fun insertShows(showsEntity: ArrayList<TvShowsEntity>) = mCDao.insertShows(showsEntity)

    fun updateShows(showsEntity: TvShowsEntity) = mCDao.updateShows(showsEntity)

    fun getFavShow(sort: String): DataSource.Factory<Int, FavoriteTvShowsEntity> {
        val query = SortUtils.getSortedShows(sort)
        return mCDao.getFavShow(query)
    }

    fun checkFavShow(id : Int) = mCDao.checkShow(id)

    fun insertFavShow(favoriteShow: FavoriteTvShowsEntity) = mCDao.insertFavShow(favoriteShow)

    fun deleteFavShow(favoriteShow: FavoriteTvShowsEntity) = mCDao.deleteFavShow(favoriteShow)

    fun getFavSDetail(id: String) = mCDao.getFavShowDetail(id)

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mCDao: CDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(mCDao)
    }

}
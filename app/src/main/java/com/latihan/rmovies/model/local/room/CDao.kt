package com.latihan.rmovies.model.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity

@Dao
interface CDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): DataSource.Factory<Int, MoviesEntity>

    @Transaction
    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovieDetail(movieId: String): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Update
    fun updateMovies(movies: MoviesEntity)

    @Query("SELECT * FROM movie WHERE favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MoviesEntity>


    @Query("SELECT * FROM tvshow")
    fun getTvShows(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM tvshow WHERE id = :showId")
    fun getTvShowDetail(showId: String): LiveData<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShows(shows: List<TvShowsEntity>)

    @Update
    fun updateShows(shows: TvShowsEntity)

    @Query("SELECT * FROM tvshow WHERE favorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowsEntity>
}
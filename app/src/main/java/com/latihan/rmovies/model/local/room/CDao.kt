package com.latihan.rmovies.model.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity
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


    @Query("SELECT * FROM tvshow")
    fun getTvShows(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM tvshow WHERE id = :showId")
    fun getTvShowDetail(showId: String): LiveData<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShows(shows: List<TvShowsEntity>)

    @Update
    fun updateShows(shows: TvShowsEntity)

    @RawQuery(observedEntities = [TvShowsEntity::class])
    fun getFavoriteTvShow(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShowsEntity>


    @RawQuery(observedEntities = [FavoriteMoviesEntity::class])
    fun getFavMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, FavoriteMoviesEntity>

    @Transaction
    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    fun getFavMovieDetail(movieId: String): LiveData<FavoriteMoviesEntity>

    @Query("SELECT count(*) FROM favorite_movies WHERE id = :id")
    fun checkMovies(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavMovies(movies: FavoriteMoviesEntity)

    @Delete
    fun deleteFavMovies(movies: FavoriteMoviesEntity)


    @RawQuery(observedEntities = [FavoriteTvShowsEntity::class])
    fun getFavShow(query: SimpleSQLiteQuery): DataSource.Factory<Int, FavoriteTvShowsEntity>

    @Transaction
    @Query("SELECT * FROM favorite_show WHERE id = :movieId")
    fun getFavShowDetail(movieId: String): LiveData<FavoriteTvShowsEntity>

    @Query("SELECT count(*) FROM favorite_show WHERE id = :id")
    fun checkShow(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavShow(movies: FavoriteTvShowsEntity)

    @Delete
    fun deleteFavShow(movies: FavoriteTvShowsEntity)
}
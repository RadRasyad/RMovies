package com.latihan.rmovies.model.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.latihan.rmovies.model.local.entity.Item

@Dao
interface CDao {

    @Query("SELECT * FROM item")
    fun getMovies(): DataSource.Factory<Int, Item>

    @Query("SELECT * FROM item")
    fun getTvShows(): DataSource.Factory<Int, Item>

    @Query("SELECT * FROM item")
    fun getDetailMovies(movieId: String)
}
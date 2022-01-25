package com.latihan.rmovies.model.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.latihan.rmovies.model.local.entity.FavoriteTvShowsEntity
import com.latihan.rmovies.model.local.entity.FavoriteMoviesEntity
import com.latihan.rmovies.model.local.entity.MoviesEntity
import com.latihan.rmovies.model.local.entity.TvShowsEntity

@Database(entities = [MoviesEntity::class, TvShowsEntity::class, FavoriteMoviesEntity::class, FavoriteTvShowsEntity::class], version = 1, exportSchema = false)
abstract class CDatabase : RoomDatabase() {
    abstract fun dao(): CDao

    companion object {

        @Volatile
        private var INSTANCE: CDatabase? = null

        fun getInstance(context: Context): CDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CDatabase::class.java,
                    "CDatabase.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }

}
package com.latihan.rmovies.model.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class MoviesEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "releasedDate")
    val releasedDate: String?,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double?,

    @ColumnInfo(name = "posterPath")
    val posterPath: String?,

    @ColumnInfo(name = "backdropPath")
    val backdropPath: String?,

    @ColumnInfo(name = "favorite")
    var favoriteMovies: Boolean = false

)

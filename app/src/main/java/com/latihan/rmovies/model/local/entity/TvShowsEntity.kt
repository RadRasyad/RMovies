package com.latihan.rmovies.model.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshow")
data class TvShowsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "firstAirDate")
    val firstAirDate: String?,

    @ColumnInfo(name = "voteAverage")
    val voteAverage: Double?,

    @ColumnInfo(name = "posterPath")
    val posterPath: String?,

    @ColumnInfo(name = "backdropPath")
    val backdropPath: String?

)

package com.latihan.rmovies.model.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item")
data class Item(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String?,

    @ColumnInfo(name = "releasedDate")
    @SerializedName("release_date")
    val releasedDate: String?,

    @ColumnInfo(name = "voteAverage")
    @SerializedName("vote_average")
    val voteAverage: Double?,

    @ColumnInfo(name = "posterPath")
    @SerializedName("poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "backdropPath")
    @SerializedName("backdrop_path")
    val backdropPath: String?

)
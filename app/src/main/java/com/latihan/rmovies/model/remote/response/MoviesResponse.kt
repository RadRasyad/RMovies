package com.latihan.rmovies.model.remote.response


import com.google.gson.annotations.SerializedName

data class MoviesResponse (

    @SerializedName("results")
    val list: List<Movies>
)

data class Movies(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val releasedDate: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?

)
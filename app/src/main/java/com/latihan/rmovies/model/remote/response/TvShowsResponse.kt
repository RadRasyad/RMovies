package com.latihan.rmovies.model.remote.response


import com.google.gson.annotations.SerializedName

data class TvShowsResponse (
    @SerializedName("results")
    val list: List<TvShows>
)

data class TvShows(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("first_air_date")
    val firstAirDate: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?
)
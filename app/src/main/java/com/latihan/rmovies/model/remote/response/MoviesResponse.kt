package com.latihan.rmovies.model.remote.response

import com.google.gson.annotations.SerializedName
import com.latihan.rmovies.model.local.entity.MoviesEntity

data class MoviesResponse (
    @SerializedName("results")
    val list: List<MoviesEntity>?
)
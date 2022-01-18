package com.latihan.rmovies.model.remote.response

import com.google.gson.annotations.SerializedName
import com.latihan.rmovies.model.local.entity.TvShowsEntity

data class TvShowsResponse (
    @SerializedName("results")
    val list: List<TvShowsEntity>
)
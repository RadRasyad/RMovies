package com.latihan.rmovies.model.remote

import com.google.gson.annotations.SerializedName
import com.latihan.rmovies.model.local.entity.Item

data class ItemResponse (
    @SerializedName("results")
    val list: List<Item>
)
package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("season")
    val season: String,

    @SerializedName("year")
    val year: Int
)

package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class SeasonalAnime(
    @SerializedName("data")
    val data: List<SeasonalData>,

    @SerializedName("paging")
    val paging: Paging,

    @SerializedName("season")
    val season: Season
)
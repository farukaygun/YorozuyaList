package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class RankingAnime(
    @SerializedName("data")
    val data: List<AnimeData>,

    @SerializedName("paging")
    val paging: Paging
)

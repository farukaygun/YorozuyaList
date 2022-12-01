package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class AnimeData(
    @SerializedName("node")
    val node: Node,

    @SerializedName("ranking")
    val ranking: Ranking?,

    @SerializedName("ranking_type")
    var rankingType: String? = null,
)
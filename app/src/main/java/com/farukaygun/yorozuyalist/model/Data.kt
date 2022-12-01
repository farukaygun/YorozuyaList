package com.farukaygun.yorozuyalist.model

import com.farukaygun.yorozuyalist.model.Node
import com.farukaygun.yorozuyalist.model.Ranking
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("node")
    val node: Node,

    @SerializedName("ranking")
    val ranking: Ranking?,

    @SerializedName("ranking_type")
    var rankingType: String? = null,
)
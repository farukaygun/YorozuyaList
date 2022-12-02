package com.farukaygun.yorozuyalist.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("node")
    val node: Node,

    @SerializedName("ranking")
    val ranking: Ranking?,

    @SerializedName("ranking_type")
    var rankingType: String? = null,

    @SerializedName("list_status")
    val listStatus: ListStatus
)
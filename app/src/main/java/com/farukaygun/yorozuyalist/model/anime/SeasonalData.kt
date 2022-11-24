package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class SeasonalData(
    @SerializedName("node")
    val node: SeasonalNode
)

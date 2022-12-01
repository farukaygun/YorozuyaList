package com.farukaygun.yorozuyalist.model.anime

import com.farukaygun.yorozuyalist.model.Node
import com.google.gson.annotations.SerializedName

data class Recommendation(
    @SerializedName("node")
    val node: Node,
    @SerializedName("num_recommendations")
    val numRecommendations: Int
)

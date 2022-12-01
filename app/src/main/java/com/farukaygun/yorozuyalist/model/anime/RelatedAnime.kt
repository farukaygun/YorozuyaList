package com.farukaygun.yorozuyalist.model.anime

import com.farukaygun.yorozuyalist.model.Node
import com.google.gson.annotations.SerializedName

data class RelatedAnime(
    @SerializedName("node")
    val node: Node,

    @SerializedName("relation_type")
    val relationType: String,

    @SerializedName("relation_type_formatted")
    val relationTypeFormatted: String
)
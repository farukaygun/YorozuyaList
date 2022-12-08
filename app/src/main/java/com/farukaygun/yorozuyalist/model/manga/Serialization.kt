package com.farukaygun.yorozuyalist.model.manga

import com.google.gson.annotations.SerializedName

data class Serialization(
    @SerializedName("node")
    val node: SerializationNode
)

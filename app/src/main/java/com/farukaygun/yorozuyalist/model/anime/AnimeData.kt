package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class AnimeData(
    @SerializedName("node")
    val node: AnimeNode
)

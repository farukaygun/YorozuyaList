package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class SeasonalNode(
    @SerializedName("id")
    val id: Int,

    @SerializedName("main_picture")
    val mainPicture: MainPicture,

    @SerializedName("title")
    val title: String
)

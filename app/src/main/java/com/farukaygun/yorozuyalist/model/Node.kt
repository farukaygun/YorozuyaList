package com.farukaygun.yorozuyalist.model

import com.farukaygun.yorozuyalist.model.anime.MainPicture
import com.google.gson.annotations.SerializedName

data class Node(
    @SerializedName("id")
    val id: Int,

    @SerializedName("main_picture")
    val mainPicture: MainPicture,

    @SerializedName("title")
    val title: String,

    @SerializedName("mean")
    val mean: String,

    @SerializedName("media_type")
    val mediaType: String,

    @SerializedName(value = "num_episodes", alternate = ["num_chapters"])
    val numEpisodes: Int,

    @SerializedName("num_list_users")
    val numListUsers: Int
)

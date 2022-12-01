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

    @SerializedName("num_episodes")
    val numEpisodes: String,

    @SerializedName("num_list_users")
    val numListUsers: String
)

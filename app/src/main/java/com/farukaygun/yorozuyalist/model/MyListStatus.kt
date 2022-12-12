package com.farukaygun.yorozuyalist.model

import com.google.gson.annotations.SerializedName

data class MyListStatus(
    @SerializedName("status")
    val status: String,

    @SerializedName("score")
    val score: Int,

    @SerializedName("num_episodes_watched")
    val numEpisodesWatched: Int,

    @SerializedName("num_chapters_read")
    val numChaptersRead: Int,

    @SerializedName("is_rewatching")
    val isRewatching: Boolean,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("finish_date")
    val finishDate: String
)
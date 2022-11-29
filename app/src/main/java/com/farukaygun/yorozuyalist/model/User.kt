package com.farukaygun.yorozuyalist.model
import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("anime_statistics")
    val animeStatistics: AnimeStatistics,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("joined_at")
    val joinedAt: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val picture: String
)
package com.farukaygun.yorozuyalist.model.user

import com.google.gson.annotations.SerializedName


data class User(
	@SerializedName("anime_statistics")
	val userAnimeStatistics: UserAnimeStatistics,
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
	val picture: String,
)
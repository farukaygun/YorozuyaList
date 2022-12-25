package com.farukaygun.yorozuyalist.model

import com.google.gson.annotations.SerializedName

data class ListStatus(
	@SerializedName("status")
	val status: String,

	@SerializedName("score")
	val score: Int,

	@SerializedName(value = "num_episodes_watched", alternate = ["num_chapters_read"])
	val numEpisodesWatched: Int,

	@SerializedName("is_rewatching")
	val isRewatching: Boolean,

	@SerializedName("updated_at")
	val updatedAt: String,
)
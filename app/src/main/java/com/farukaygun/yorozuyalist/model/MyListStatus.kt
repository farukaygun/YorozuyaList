package com.farukaygun.yorozuyalist.model

import com.google.gson.annotations.SerializedName

data class MyListStatus(
	@SerializedName("status")
	var status: String,

	@SerializedName("score")
	var score: Int,

	@SerializedName("num_episodes_watched")
	var numEpisodesWatched: Int,

	@SerializedName("num_chapters_read")
	var numChaptersRead: Int,

	@SerializedName("is_rewatching")
	val isRewatching: Boolean,

	@SerializedName("updated_at")
	val updatedAt: String,

	@SerializedName("finish_date")
	val finishDate: String,
)
package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class Status(
	@SerializedName("watching")
	val watching: String,

	@SerializedName("completed")
	val completed: String,

	@SerializedName("on_hold")
	val onHold: String,

	@SerializedName("dropped")
	val dropped: String,

	@SerializedName("plan_to_watch")
	val planToWatch: String,
)
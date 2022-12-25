package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class Studio(
	@SerializedName("id")
	val id: Int,

	@SerializedName("name")
	val name: String,
)
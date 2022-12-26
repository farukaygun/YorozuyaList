package com.farukaygun.yorozuyalist.model

import com.google.gson.annotations.SerializedName

data class Ranking(
	@SerializedName("rank")
	val rank: Int,
)
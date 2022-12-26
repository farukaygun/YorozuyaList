package com.farukaygun.yorozuyalist.model.anime

import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Paging
import com.google.gson.annotations.SerializedName

data class SeasonalAnime(
	@SerializedName("data")
	val data: List<Data>,

	@SerializedName("paging")
	val paging: Paging,

	@SerializedName("season")
	val season: Season,
)
package com.farukaygun.yorozuyalist.model

import com.google.gson.annotations.SerializedName

data class Related(
	@SerializedName("node")
	val node: Node,

	@SerializedName("relation_type")
	val relationType: String,

	@SerializedName("relation_type_formatted")
	val relationTypeFormatted: String,
)
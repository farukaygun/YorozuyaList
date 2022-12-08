package com.farukaygun.yorozuyalist.model.manga

import com.google.gson.annotations.SerializedName

data class SerializationNode(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
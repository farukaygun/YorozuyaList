package com.farukaygun.yorozuyalist.model.manga

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("node")
    val node: AuthorNode,

    @SerializedName("role")
    val role: String
)
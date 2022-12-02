package com.farukaygun.yorozuyalist.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("data")
    val data: List<Data>,

    @SerializedName("paging")
    val paging: Paging
)

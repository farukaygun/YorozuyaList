package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("status")
    val status: Status,

    @SerializedName("num_list_users")
    val numListUsers: Int
)
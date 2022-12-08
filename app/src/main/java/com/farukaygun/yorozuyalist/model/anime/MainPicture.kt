package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class MainPicture(
    @SerializedName("medium")
    val medium: String,
    
    @SerializedName("large")
    val large: String
)
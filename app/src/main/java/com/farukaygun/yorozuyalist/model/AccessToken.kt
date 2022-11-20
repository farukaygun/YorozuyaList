package com.farukaygun.yorozuyalist.model

import com.google.gson.annotations.SerializedName


data class AccessToken(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("expires_in")
    val expiresIn: Double,

    @SerializedName("refresh_token")
    val refreshToken: String,

    @SerializedName("token_type")
    val tokenType: String
)
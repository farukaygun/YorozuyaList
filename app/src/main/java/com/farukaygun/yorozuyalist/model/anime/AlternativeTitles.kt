package com.farukaygun.yorozuyalist.model.anime

import com.google.gson.annotations.SerializedName

data class AlternativeTitles(
    @SerializedName("synonyms")
    val synonyms: List<String>,
    @SerializedName("en")
    val en: String,
    @SerializedName("ja")
    val ja: String
)
package com.farukaygun.yorozuyalist.model.manga
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Paging
import com.google.gson.annotations.SerializedName

data class RankingManga(
    @SerializedName("data")
    val data: List<Data>,

    @SerializedName("paging")
    val paging: Paging
)
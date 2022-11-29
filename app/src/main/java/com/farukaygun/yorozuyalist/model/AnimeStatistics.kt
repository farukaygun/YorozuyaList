package com.farukaygun.yorozuyalist.model

import com.google.gson.annotations.SerializedName

data class AnimeStatistics(
    @SerializedName("mean_score")
    val meanScore: Double,
    @SerializedName("num_days")
    val numDays: Double,
    @SerializedName("num_days_completed")
    val numDaysCompleted: Double,
    @SerializedName("num_days_dropped")
    val numDaysDropped: Double,
    @SerializedName("num_days_on_hold")
    val numDaysOnHold: Int,
    @SerializedName("num_days_watched")
    val numDaysWatched: Double,
    @SerializedName("num_days_watching")
    val numDaysWatching: Double,
    @SerializedName("num_episodes")
    val numEpisodes: Int,
    @SerializedName("num_times_rewatched")
    val numTimesRewatched: Int,
    @SerializedName("num_items")
    val numItems: Float,
    @SerializedName("num_items_completed")
    val numItemsCompleted: Int,
    @SerializedName("num_items_dropped")
    val numItemsDropped: Int,
    @SerializedName("num_items_on_hold")
    val numItemsOnHold: Int,
    @SerializedName("num_items_plan_to_watch")
    val numItemsPlanToWatch: Int,
    @SerializedName("num_items_watching")
    val numItemsWatching: Int
)
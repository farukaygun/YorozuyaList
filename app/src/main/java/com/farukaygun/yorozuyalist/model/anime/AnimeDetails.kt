package com.farukaygun.yorozuyalist.model.anime
import com.farukaygun.yorozuyalist.model.*
import com.google.gson.annotations.SerializedName


data class AnimeDetails(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("main_picture")
    val mainPicture: MainPicture,
    @SerializedName("alternative_titles")
    val alternativeTitles: AlternativeTitles,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("mean")
    val mean: Double,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("num_list_users")
    val numListUsers: Int,
    @SerializedName("num_scoring_users")
    val numScoringUsers: Int,
    @SerializedName("nsfw")
    val nsfw: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("num_episodes")
    val numEpisodes: Int,
    @SerializedName("my_list_status")
    val myListStatus: MyListStatus,
    @SerializedName("start_season")
    val startSeason: StartSeason,
    @SerializedName("broadcast")
    val broadcast: Broadcast,
    @SerializedName("source")
    val source: String,
    @SerializedName("average_episode_duration")
    val averageEpisodeDuration: Int,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("pictures")
    val pictures: List<MainPicture>,
    @SerializedName("background")
    val background: String,
    @SerializedName("related_anime")
    val related: List<Related>,
    @SerializedName("related_manga")
    val relatedManga: List<Any>,
    @SerializedName("recommendations")
    val recommendations: List<Recommendation>,
    @SerializedName("studios")
    val studios: List<Studio>,
    @SerializedName("statistics")
    val statistics: Statistics
)
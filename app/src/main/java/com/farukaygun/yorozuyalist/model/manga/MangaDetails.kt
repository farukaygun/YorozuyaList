package com.farukaygun.yorozuyalist.model.manga

import com.farukaygun.yorozuyalist.model.*
import com.farukaygun.yorozuyalist.model.anime.MainPicture
import com.google.gson.annotations.SerializedName


data class MangaDetails(
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
	@SerializedName("num_volumes")
	val numVolumes: Int,
	@SerializedName("num_chapters")
	val numChapters: Int,
	@SerializedName("my_list_status")
	val myListStatus: MyListStatus,
	@SerializedName("authors")
	val authors: List<Author>,
	@SerializedName("pictures")
	val pictures: List<MainPicture>,
	@SerializedName("background")
	val background: String,
	@SerializedName("related_anime")
	val relatedAnime: List<Any>,
	@SerializedName("related_manga")
	val relatedManga: List<Related>,
	@SerializedName("recommendations")
	val recommendations: List<Recommendation>,
	@SerializedName("serialization")
	val serialization: List<Serialization>,
)
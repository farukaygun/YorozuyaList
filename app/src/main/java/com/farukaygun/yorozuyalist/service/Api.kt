package com.farukaygun.yorozuyalist.service

import com.farukaygun.yorozuyalist.model.AccessToken
import com.farukaygun.yorozuyalist.model.MyListStatus
import com.farukaygun.yorozuyalist.model.anime.AnimeDetails
import com.farukaygun.yorozuyalist.model.user.User
import com.farukaygun.yorozuyalist.model.anime.SeasonalAnime
import com.farukaygun.yorozuyalist.model.anime.SuggestedAnime
import com.farukaygun.yorozuyalist.model.manga.MangaDetails
import com.farukaygun.yorozuyalist.model.Response as ResponseApi
import com.farukaygun.yorozuyalist.util.Constants.BASE_API_URL
import com.farukaygun.yorozuyalist.util.Constants.OAUTH2_URL
import com.farukaygun.yorozuyalist.util.SharedPrefsHelper
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api : BaseResponseHandler() {
	private fun createRetrofit(baseUrl: String): IApi {
		val interceptor = HttpLoggingInterceptor()
		interceptor.level = HttpLoggingInterceptor.Level.BODY

		val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

		val retrofit = Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build()

		return retrofit.create(IApi::class.java)
	}

	suspend fun getAccessToken(
        clientId: String,
        code: String,
        codeVerifier: String,
        grantType: String,
    ): ResponseHandler<AccessToken> {
		return safeApiCall {
			createRetrofit(OAUTH2_URL).getAccessToken(
				clientId,
				code,
				codeVerifier,
				grantType
			)
		}
	}

	suspend fun getSeasonalAnime(year: Int, season: String): ResponseHandler<SeasonalAnime> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getSeasonalAnime(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				year,
				season,
				sort = "anime_score",
				limit = 10,
				offset = 0,
				fields = "mean,media_type,num_episodes,num_list_users"
			)
		}
	}

	suspend fun getSeasonalAnimePaging(url: String): ResponseHandler<SeasonalAnime> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getSeasonalAnime(
				url = url,
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
			)
		}
	}

	suspend fun getSuggestedAnime(): ResponseHandler<SuggestedAnime> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getSuggestedAnime(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				limit = 10,
				offset = 0,
				fields = ""
			)
		}
	}

	suspend fun getUser(): ResponseHandler<User> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getUser(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				fields = "anime_statistics"
			)
		}
	}

	suspend fun getAnimeRanking(rankingType: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getAnimeRanking(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				rankingType = rankingType,
				limit = 50,
				fields = "mean,media_type,num_episodes,num_chapters,num_list_users"
			)
		}
	}

	suspend fun getAnimeRankingPaging(url: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getAnimeRanking(
				url = url,
				header = "Bearer " + SharedPrefsHelper().getString("accessToken")
			)
		}
	}

	suspend fun getMangaRanking(rankingType: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getMangaRanking(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				rankingType = rankingType,
				limit = 50,
				fields = "mean,media_type,num_episodes,num_chapters,num_list_users"
			)
		}
	}

	suspend fun getMangaRankingPaging(url: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getMangaRanking(
				url = url,
				header = "Bearer " + SharedPrefsHelper().getString("accessToken")
			)
		}
	}

	suspend fun getUserAnimeList(status: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getUserAnimeList(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				status = status,
				sort = "anime_title",
				fields = "list_status,num_episodes,media_type,status,num_list_users,mean"
			)
		}
	}

	suspend fun getUserAnimeListPaging(url: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getUserMangaList(
				url = url,
				header = "Bearer " + SharedPrefsHelper().getString("accessToken")
			)
		}
	}

	suspend fun getUserMangaList(status: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getUserMangaList(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				status = status,
				sort = "manga_title",
				fields = "list_status,num_chapters,media_type,status,num_list_users,mean"
			)
		}
	}

	suspend fun getUserMangaListPaging(url: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getUserMangaList(
				url = url,
				header = "Bearer " + SharedPrefsHelper().getString("accessToken")
			)
		}
	}

	suspend fun getAnimeDetails(animeId: Int): ResponseHandler<AnimeDetails> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getAnimeDetails(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				animeId = animeId,
				fields = "id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity," +
						"num_list_users,num_scoring_users,media_type,status,genres,my_list_status,num_episodes,start_season,broadcast," +
						"source,average_episode_duration,studios,opening_themes,ending_themes,related_anime{media_type},related_manga{media_type}"
			)
		}
	}

	suspend fun getMangaDetails(mangaId: Int): ResponseHandler<MangaDetails> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getMangaDetails(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				mangaId = mangaId,
				fields = "id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity," +
						"num_list_users,num_scoring_users,media_type,status,genres,my_list_status,num_chapters,num_volumes," +
						"source,authors{first_name,last_name},serialization,related_anime{media_type},related_manga{media_type}"
			)
		}
	}

	suspend fun updateUserAnimeList(
        animeId: Int,
        status: String,
        score: Int?,
        numWatchedEpisodes: Int,
    ): ResponseHandler<MyListStatus> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).updateUserAnimeList(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				animeId = animeId,
				status = status,
				score = score,
				numWatchedEpisodes = numWatchedEpisodes
			)
		}
	}

	suspend fun deleteUserAnimeList(animeId: Int): ResponseHandler<ResponseBody> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).deleteUserAnimeList(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				animeId = animeId)
		}
	}

	suspend fun updateUserMangaList(
        mangaId: Int,
        status: String,
        score: Int?,
        numReadedChapters: Int,
    ): ResponseHandler<MyListStatus> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).updateUserMangaList(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				mangaId = mangaId,
				status = status,
				score = score,
				numReadedChapters = numReadedChapters
			)
		}
	}

	suspend fun deleteUserMangaList(mangaId: Int): ResponseHandler<ResponseBody> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).deleteUserMangaList(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				mangaId = mangaId)
		}
	}

	suspend fun getSearchAnimeList(query: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getSearchAnimeList(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				query = query,
				fields = "id,title,main_picture,mean,media_type,num_episodes,start_season,status"
			)
		}
	}

	suspend fun getSearchAnimeListPaging(url: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getSearchAnimeList(
				url = url,
				header = "Bearer " + SharedPrefsHelper().getString("accessToken")
			)
		}
	}

	suspend fun getSearchMangaList(query: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getSearchMangaList(
				header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
				query = query,
				fields = "id,title,main_picture,mean,media_type,num_chapters,start_season,status"
			)
		}
	}

	suspend fun getSearchMangaListPaging(url: String): ResponseHandler<ResponseApi> {
		return safeApiCall {
			createRetrofit(BASE_API_URL).getSearchMangaList(
				url = url,
				header = "Bearer " + SharedPrefsHelper().getString("accessToken")
			)
		}
	}
}
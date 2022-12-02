package com.farukaygun.yorozuyalist.service

import com.farukaygun.yorozuyalist.model.AccessToken
import com.farukaygun.yorozuyalist.model.user.User
import com.farukaygun.yorozuyalist.model.anime.SeasonalAnime
import com.farukaygun.yorozuyalist.model.anime.SuggestedAnime
import com.farukaygun.yorozuyalist.model.Response as ResponseApi
import com.farukaygun.yorozuyalist.util.Constants.BASE_API_URL
import com.farukaygun.yorozuyalist.util.Constants.OAUTH2_URL
import com.farukaygun.yorozuyalist.util.SharedPrefsHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api : BaseResponseHandler() {
    private fun createRetrofit(baseUrl: String) : IApi {
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
        grantType: String
    ): ResponseHandler<AccessToken> {
        return safeApiCall { createRetrofit(OAUTH2_URL).getAccessToken(
            clientId,
            code,
            codeVerifier,
            grantType
        ) }
    }

    suspend fun getSeasonalAnime(year: Int, season: String): ResponseHandler<SeasonalAnime> {
        return safeApiCall { createRetrofit(BASE_API_URL).getSeasonalAnime(
            header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
            year,
            season,
            sort = "anime_score",
            limit = 10,
            offset = 0,
            fields = ""
        ) }
    }

    suspend fun getSuggestedAnime(): ResponseHandler<SuggestedAnime> {
        return safeApiCall { createRetrofit(BASE_API_URL).getSuggestedAnime(
            header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
            limit = 10,
            offset = 0,
            fields = ""
        ) }
    }

    suspend fun getUser(): ResponseHandler<User> {
        return safeApiCall { createRetrofit(BASE_API_URL).getUser(
            header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
            fields = "anime_statistics"
        ) }
    }

    suspend fun getAnimeRanking(rankingType: String): ResponseHandler<ResponseApi> {
        return safeApiCall { createRetrofit(BASE_API_URL).getAnimeRanking(
            header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
            rankingType = rankingType,
            limit = 100,
            fields = "mean,media_type,num_episodes,num_chapters,num_list_users"
        ) }
    }

    suspend fun getMangaRanking(rankingType: String): ResponseHandler<ResponseApi> {
        return safeApiCall { createRetrofit(BASE_API_URL).getMangaRanking(
            header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
            rankingType = rankingType,
            limit = 100,
            fields = "mean,media_type,num_episodes,num_chapters,num_list_users"
        ) }
    }

    suspend fun getUserAnimeList(status: String): ResponseHandler<ResponseApi> {
        return safeApiCall { createRetrofit(BASE_API_URL).getUserAnimeList(
            header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
            status = status,
            sort = "anime_title",
            fields = "list_status,num_episodes,media_type,status,num_list_users,mean"
        ) }
    }

    suspend fun getUserMangaList(status: String): ResponseHandler<ResponseApi> {
        return safeApiCall { createRetrofit(BASE_API_URL).getUserMangaList(
            header = "Bearer " + SharedPrefsHelper().getString("accessToken"),
            status = status,
            sort = "manga_title",
            fields = "list_status,num_chapters,num_volumes,media_type,status,num_list_users,mean"
        ) }
    }
}
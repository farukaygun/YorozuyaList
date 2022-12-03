package com.farukaygun.yorozuyalist.service

import com.farukaygun.yorozuyalist.model.AccessToken
import com.farukaygun.yorozuyalist.model.user.User
import com.farukaygun.yorozuyalist.model.Response as ResponseApi
import com.farukaygun.yorozuyalist.model.anime.SeasonalAnime
import com.farukaygun.yorozuyalist.model.anime.SuggestedAnime
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface IApi {
    @FormUrlEncoded
    @POST("token")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("code") code: String,
        @Field("code_verifier") codeVerifier: String,
        @Field("grant_type") grantType: String
    ): Response<AccessToken>

    @GET("anime/season/{year}/{season}")
    suspend fun getSeasonalAnime(
        @Header("AUTHORIZATION") header: String,
        @Path("year") year: Int,
        @Path("season") season: String,
        @Query("sort") sort: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("fields") fields: String,
    ): Response<SeasonalAnime>

    @GET
    suspend fun getSeasonalAnime(
        @Url url: String,
        @Header("AUTHORIZATION") header: String,
    ): Response<SeasonalAnime>

    @GET("anime/suggestions")
    suspend fun getSuggestedAnime(
        @Header("AUTHORIZATION") header: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("fields") fields: String,
    ): Response<SuggestedAnime>

    @GET("users/@me")
    suspend fun getUser(
        @Header("AUTHORIZATION") header: String,
        @Query("fields") fields: String,
    ): Response<User>

    @GET("anime/ranking")
    suspend fun getAnimeRanking(
        @Header("AUTHORIZATION") header: String,
        @Query("ranking_type") rankingType: String,
        @Query("limit") limit: Int,
        @Query("fields") fields: String
    ): Response<ResponseApi>

    @GET
    suspend fun getAnimeRanking(
        @Url url: String,
        @Header("AUTHORIZATION") header: String
    ): Response<ResponseApi>

    @GET("manga/ranking")
    suspend fun getMangaRanking(
        @Header("AUTHORIZATION") header: String,
        @Query("ranking_type") rankingType: String,
        @Query("limit") limit: Int,
        @Query("fields") fields: String
    ): Response<ResponseApi>

    @GET
    suspend fun getMangaRanking(
        @Url url: String,
        @Header("AUTHORIZATION") header: String
    ): Response<ResponseApi>

    @GET("users/@me/animelist")
    suspend fun getUserAnimeList(
        @Header("AUTHORIZATION") header: String,
        @Query("status") status: String,
        @Query("sort") sort: String,
        @Query("fields") fields: String
    ): Response<ResponseApi>

    @GET
    suspend fun getUserAnimeList(
        @Url url: String,
        @Header("AUTHORIZATION") header: String
    ): Response<ResponseApi>

    @GET("users/@me/mangalist")
    suspend fun getUserMangaList(
        @Header("AUTHORIZATION") header: String,
        @Query("status") status: String,
        @Query("sort") sort: String,
        @Query("fields") fields: String
    ): Response<ResponseApi>

    @GET
    suspend fun getUserMangaList(
        @Url url: String,
        @Header("AUTHORIZATION") header: String
    ): Response<ResponseApi>

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(
        @Path("anime_id") animeId: Int,
        @Header("AUTHORIZATION") header: String,
        @Query("fields") fields: String
    ) : Response<ResponseApi>
}
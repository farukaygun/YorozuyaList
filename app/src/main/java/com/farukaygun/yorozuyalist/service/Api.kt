package com.farukaygun.yorozuyalist.service

import com.farukaygun.yorozuyalist.model.AccessToken
import com.farukaygun.yorozuyalist.model.anime.SeasonalAnime
import com.farukaygun.yorozuyalist.util.Constants.BASE_API_URL
import com.farukaygun.yorozuyalist.util.Constants.OAUTH2_URL
import com.farukaygun.yorozuyalist.util.SharedPrefsHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api : BaseResponseHandler() {
    private fun createRetrofit(baseUrl: String) : IApi {
// For logging purpose.
//         val interceptor = HttpLoggingInterceptor()
//          interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//         val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
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
        )  }
    }
}
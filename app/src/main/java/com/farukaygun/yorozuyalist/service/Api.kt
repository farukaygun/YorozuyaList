package com.farukaygun.yorozuyalist.service

import android.util.Log
import com.farukaygun.yorozuyalist.model.AccessToken
import com.farukaygun.yorozuyalist.util.Constants.OAUTH2_URL
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    private fun createRetrofit() : IApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(OAUTH2_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(IApi::class.java)
    }

    suspend fun getAccessToken(
        clientId: String,
        code: String,
        codeVerifier: String
    ): AccessToken? {
        val response = createRetrofit().getAccessToken(
            clientId,
            code,
            codeVerifier,
        )
        var responseBody: AccessToken? = null

        if (response.isSuccessful) {
            response.body()?.let {
                responseBody = Gson().fromJson(it.toString(), AccessToken::class.java)
            }
        } else {
            Log.e("Response Body Error", "getAccessToken: ${response.errorBody()}")
        }
        return responseBody
    }
}
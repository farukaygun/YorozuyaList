package com.farukaygun.yorozuyalist.service

import com.farukaygun.yorozuyalist.util.Constants.BASE_API_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Api {
    private val api = Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
        .create(IApi::class.java)

    suspend fun userAuthorisation(
        responseType: String,
        clientId: String,
        codeChallenge: String,
        state: String
    ) : Response<Any> {
        val response = api.userAuthorisation(responseType, clientId, codeChallenge, state)
        println(response)
        return response
    }
}
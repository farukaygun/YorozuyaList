package com.farukaygun.yorozuyalist.service

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IApi {
    @FormUrlEncoded
    @POST("token")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("code") code: String,
        @Field("code_verifier") codeVerifier: String,
        @Field("grant_type") grantType: String = "authorization_code"
    ) : Response<Any>
}
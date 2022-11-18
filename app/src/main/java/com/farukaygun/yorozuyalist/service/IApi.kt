package com.farukaygun.yorozuyalist.service

import com.farukaygun.yorozuyalist.util.CodeVerifier
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IApi {
    @GET("authorize")
    suspend fun userAuthorisation(
        @Query("response_type") responseType : String,
        @Query("client_id") clientId : String,
        @Query("code_challenge") codeChallenge : String,
        @Query("state") state : String
    ) : Response<Any>
}
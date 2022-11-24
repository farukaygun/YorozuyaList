package com.farukaygun.yorozuyalist.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseResponseHandler {
    suspend fun <T> safeApiCall(apiCall: suspend() -> Response<T>): ResponseHandler<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiCall()

                if (response.isSuccessful) {
                    ResponseHandler.Success(data = response.body()!!)
                } else {
                    val errorResponse = response.errorBody().toString()
                    ResponseHandler.Error(errorMessage = errorResponse)
                }
            } catch (e: HttpException) {
                ResponseHandler.Error(errorMessage = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                ResponseHandler.Error("Please check your network connection")
            } catch (e: Exception) {
                ResponseHandler.Error(errorMessage = "Something went wrong")
            }
        }
    }
}
package com.farukaygun.yorozuyalist.service

sealed class ResponseHandler<T>(
    val data: T? = null,
    val message: String? = null,
) {
	class Success<T>(data: T) : ResponseHandler<T>(data = data)

	class Error<T>(errorMessage: String) : ResponseHandler<T>(message = errorMessage)

	class Loading<T> : ResponseHandler<T>()
}

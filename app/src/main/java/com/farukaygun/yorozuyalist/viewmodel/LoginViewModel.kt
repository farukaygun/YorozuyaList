package com.farukaygun.yorozuyalist.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farukaygun.yorozuyalist.model.AccessToken
import com.farukaygun.yorozuyalist.private.Constants.CLIENT_ID
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.util.CodeVerifier
import com.farukaygun.yorozuyalist.util.Constants
import com.farukaygun.yorozuyalist.util.Constants.OAUTH2_URL
import com.farukaygun.yorozuyalist.util.Constants.RESPONSE_TYPE
import com.farukaygun.yorozuyalist.util.Constants.STATE
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.ResponseBody

class LoginViewModel : ViewModel() {
    private val api = Api()
    private var job : Job? = null

    private val codeChallenge = CodeVerifier().generateCodeChallenge(64)
    val loginUrl = "${OAUTH2_URL}authorize?response_type=${RESPONSE_TYPE}&client_id=${CLIENT_ID}&code_challenge=${codeChallenge}&state=${STATE}"

    private val accessTokenFlow = MutableStateFlow<AccessToken?>(null)
    val accessToken = accessTokenFlow

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("LoginViewModel exceptionHandler", ": ${throwable.localizedMessage}")
    }


    fun parseIntentData(intent: Intent?) {
        if (intent?.data?.toString()?.startsWith(Constants.YOROZUYA_PAGELINK) == true) {
            intent.data?.let {
                val code = it.getQueryParameter("code")
                val receivedState = it.getQueryParameter("state")

                // TODO: Get token here
                if (code != null) {
                    getAccessToken(code)
                }
            }
        }
    }

    private fun getAccessToken(code: String) {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler)  {
            api.getAccessToken(
                clientId = CLIENT_ID,
                code = code,
                codeVerifier = codeChallenge
            ).let {
                accessTokenFlow.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
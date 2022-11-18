package com.farukaygun.yorozuyalist.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.farukaygun.yorozuyalist.private.Constants.clientId
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.util.CodeVerifier
import com.farukaygun.yorozuyalist.util.Constants
import com.farukaygun.yorozuyalist.util.Constants.OAUTH2_URL
import com.farukaygun.yorozuyalist.util.Constants.RESPONSE_TYPE
import com.farukaygun.yorozuyalist.util.Constants.STATE
import kotlinx.coroutines.*

class LoginViewModel : ViewModel() {
    private val api = Api()
    private var job : Job? = null

    private val codeChallenge = CodeVerifier().generateCodeChallenge(64)
    var loginUrl = "${OAUTH2_URL}authorize?response_type=${RESPONSE_TYPE}&client_id=${clientId}&code_challenge=${codeChallenge}&state=${STATE}"

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Error ${throwable.localizedMessage}")
    }

    fun parseIntentData(intent: Intent?) {
        if (intent?.data?.toString()?.startsWith(Constants.YOROZUYA_PAGELINK) == true) {
            intent.data?.let { it ->
                val code = it.getQueryParameter("code")
                val receivedState = it.getQueryParameter("state")

                println("code: $code")
                println("receivedState: $receivedState")

                // TODO: Get token here
            }
        }
    }

    /*
    @RequiresApi(Build.VERSION_CODES.O)
    fun getAccessToken() {
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler)  {
            val response = api.userAuthorisation(
                responseType = Constants.RESPONSE_TYPE,
                clientId = clientId,
                codeChallenge = codeChallenge,
                state = Constants.STATE
            )

            println("response: ${response.toString()}")

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        println("response success: ${it::class.java.typeName}")
                    }
                } else {
                    println("response error: ${response::class.java.typeName}")
                }
            }
        }
    }
    */
}
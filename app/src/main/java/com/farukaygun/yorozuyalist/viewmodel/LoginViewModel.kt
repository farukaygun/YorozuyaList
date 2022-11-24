package com.farukaygun.yorozuyalist.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.farukaygun.yorozuyalist.model.AccessToken
import com.farukaygun.yorozuyalist.private.Constants.CLIENT_ID
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.util.CodeVerifier
import com.farukaygun.yorozuyalist.util.Constants.OAUTH2_URL
import com.farukaygun.yorozuyalist.util.Constants.RESPONSE_TYPE
import com.farukaygun.yorozuyalist.util.Constants.STATE
import com.farukaygun.yorozuyalist.util.Constants.YOROZUYA_PAGELINK
import com.farukaygun.yorozuyalist.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : BaseViewModel(application) {
    private val api = Api()

    private val codeChallenge = CodeVerifier.generateCodeChallenge(64)
    val loginUrl = "${OAUTH2_URL}authorize?response_type=${RESPONSE_TYPE}&client_id=${CLIENT_ID}&code_challenge=${codeChallenge}&state=${STATE}"

    private val accessTokenFlow = MutableStateFlow<ResponseHandler<AccessToken>?>(null)
    val accessToken = accessTokenFlow


    fun openInCustomTabs(context: Context, url: String) {
        CustomTabsIntent.Builder()
            .build().apply {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                launchUrl(context, Uri.parse(url))
            }
    }

    fun parseIntentData(intent: Intent?) {
        if (intent?.data?.toString()?.startsWith(YOROZUYA_PAGELINK) == true) {
            intent.data?.let {
                val code = it.getQueryParameter("code")
                // val receivedState = it.getQueryParameter("state")

                if (code != null) {
                    getAccessToken(code)
                }
            }
        }
    }

    private fun getAccessToken(code: String) {
        launch {
            accessTokenFlow.emit(ResponseHandler.Loading())
            api.getAccessToken(
                clientId = CLIENT_ID,
                code = code,
                codeVerifier = codeChallenge,
                grantType = "authorization_code"
            ).let {
                accessTokenFlow.emit(it)
            }
        }
    }
}
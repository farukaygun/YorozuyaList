package com.farukaygun.yorozuyalist.view.user_list

import android.app.Application
import com.farukaygun.yorozuyalist.model.Response
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class UserListViewModel(application: Application): BaseViewModel(application) {
    private val api = Api()

    private val userAnimeListFlow = MutableStateFlow<ResponseHandler<Response>?>(null)
    val userAnimeList = userAnimeListFlow

    private val userMangaListFlow = MutableStateFlow<ResponseHandler<Response>?>(null)
    val userMangaList = userMangaListFlow

    fun getUserAnimeList(status: String) {
        viewModelLaunch {
            userAnimeListFlow.emit(ResponseHandler.Loading())
            api.getUserAnimeList(status).let {
                userAnimeListFlow.emit(it)
            }
        }
    }

    fun getUserMangaList(status: String) {
        viewModelLaunch {
            userMangaListFlow.emit(ResponseHandler.Loading())
            api.getUserMangaList(status).let {
                userMangaListFlow.emit(it)
            }
        }
    }
}
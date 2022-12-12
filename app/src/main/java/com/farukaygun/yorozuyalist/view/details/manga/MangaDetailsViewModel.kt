package com.farukaygun.yorozuyalist.view.details.manga

import android.app.Application
import com.farukaygun.yorozuyalist.model.MyListStatus
import com.farukaygun.yorozuyalist.model.manga.MangaDetails
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.ResponseBody

class MangaDetailsViewModel(application: Application) : BaseViewModel(application) {
    private val api = Api()

    private val userMangaDetailsFlow = MutableStateFlow<ResponseHandler<MangaDetails>?>(null)
    val mangaDetails = userMangaDetailsFlow

    private val updateUserListFlow = MutableStateFlow<ResponseHandler<MyListStatus>?>(null)
    val updateUserList = updateUserListFlow

    private val deleteUserListFlow = MutableStateFlow<ResponseHandler<ResponseBody>?>(null)
    val deleteUserList = deleteUserListFlow

    fun getMangaDetails(mangaId: Int) {
        viewModelLaunch {
            userMangaDetailsFlow.emit(ResponseHandler.Loading())
            api.getMangaDetails(mangaId).let {
                userMangaDetailsFlow.emit(it)
            }
        }
    }

    fun updateUserMangaList(mangaId: Int, status: String, score: Int?, numReadedChapters: Int) {
        viewModelLaunch {
            updateUserListFlow.emit(ResponseHandler.Loading())
            api.updateUserMangaList(mangaId, status, score, numReadedChapters).let {
                updateUserListFlow.emit(it)
            }
        }
    }

    fun deleteUserMangaList(mangaId: Int) {
        viewModelLaunch {
            deleteUserListFlow.emit(ResponseHandler.Loading())
            api.deleteUserMangaList(mangaId).let {
                deleteUserListFlow.emit(it)
            }
        }
    }
}
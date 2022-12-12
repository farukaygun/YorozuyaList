package com.farukaygun.yorozuyalist.view.details.anime

import android.app.Application
import com.farukaygun.yorozuyalist.model.MyListStatus
import com.farukaygun.yorozuyalist.model.anime.AnimeDetails
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.ResponseBody

class AnimeDetailsViewModel(application: Application) : BaseViewModel(application) {
    private val api = Api()

    private val animeAnimeDetailsFlow = MutableStateFlow<ResponseHandler<AnimeDetails>?>(null)
    val animeDetails = animeAnimeDetailsFlow

    private val updateUserListFlow = MutableStateFlow<ResponseHandler<MyListStatus>?>(null)
    val updateUserList = updateUserListFlow

    private val deleteUserListFlow = MutableStateFlow<ResponseHandler<ResponseBody>?>(null)
    val deleteUserList = deleteUserListFlow

    fun getAnimeDetails(animeId: Int) {
        viewModelLaunch {
            animeAnimeDetailsFlow.emit(ResponseHandler.Loading())
            api.getAnimeDetails(animeId).let {
                animeAnimeDetailsFlow.emit(it)
            }
        }
    }

    fun updateUserAnimeList(animeId: Int, status: String, score: Int?, numWatchedEpisodes: Int) {
        viewModelLaunch {
            updateUserListFlow.emit(ResponseHandler.Loading())
            api.updateUserAnimeList(animeId, status, score, numWatchedEpisodes).let {
                updateUserListFlow.emit(it)
            }
        }
    }

    fun deleteUserAnimeList(animeId: Int) {
        viewModelLaunch {
            deleteUserListFlow.emit(ResponseHandler.Loading())
            api.deleteUserAnimeList(animeId).let {
                deleteUserListFlow.emit(it)
            }
        }
    }
}
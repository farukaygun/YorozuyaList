package com.farukaygun.yorozuyalist.view.details.anime

import android.app.Application
import com.farukaygun.yorozuyalist.model.anime.AnimeDetails
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AnimeDetailsViewModel(application: Application) : BaseViewModel(application) {
    private val api = Api()

    private val animeAnimeDetailsFlow = MutableStateFlow<ResponseHandler<AnimeDetails>?>(null)
    val animeDetails = animeAnimeDetailsFlow

    fun getAnimeDetails(animeId: Int) {
        viewModelLaunch {
            animeAnimeDetailsFlow.emit(ResponseHandler.Loading())
            api.getAnimeDetails(animeId).let {
                animeAnimeDetailsFlow.emit(it)
            }
        }
    }
}
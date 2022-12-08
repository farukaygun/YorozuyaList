package com.farukaygun.yorozuyalist.view.details.manga

import android.app.Application
import com.farukaygun.yorozuyalist.model.manga.MangaDetails
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MangaDetailsViewModel(application: Application) : BaseViewModel(application) {
    private val api = Api()

    private val mangaAnimeDetailsFlow = MutableStateFlow<ResponseHandler<MangaDetails>?>(null)
    val mangaDetails = mangaAnimeDetailsFlow

    fun getMangaDetails(mangaId: Int) {
        viewModelLaunch {
            mangaAnimeDetailsFlow.emit(ResponseHandler.Loading())
            api.getMangaDetails(mangaId).let {
                mangaAnimeDetailsFlow.emit(it)
            }
        }
    }

}
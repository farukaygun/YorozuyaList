package com.farukaygun.yorozuyalist.view.details

import android.app.Application
import com.farukaygun.yorozuyalist.model.Details
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DetailsViewModel(application: Application) : BaseViewModel(application) {
    private val api = Api()

    private val animeDetailsFlow = MutableStateFlow<ResponseHandler<Details>?>(null)
    val animeDetails = animeDetailsFlow

    fun getAnimeDetails(animeId: Int) {
        viewModelLaunch {
            animeDetailsFlow.emit(ResponseHandler.Loading())
            api.getAnimeDetails(animeId).let {
                animeDetailsFlow.emit(it)
            }
        }
    }
}
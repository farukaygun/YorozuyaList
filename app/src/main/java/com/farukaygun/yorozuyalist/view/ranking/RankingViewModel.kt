package com.farukaygun.yorozuyalist.view.ranking

import android.app.Application
import com.farukaygun.yorozuyalist.model.anime.RankingAnime
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class RankingViewModel(application: Application) : BaseViewModel(application) {
    private val api = Api()

    private val rankingAnimeFlow = MutableStateFlow<ResponseHandler<RankingAnime>?>(null)
    val animeRanking = rankingAnimeFlow

    fun getAnimeRanking(rankingType: String) {
        viewModelLaunch {
            rankingAnimeFlow.emit(ResponseHandler.Loading())
            api.getAnimeRanking(rankingType).let {
                rankingAnimeFlow.emit(it)
            }
        }
    }
}
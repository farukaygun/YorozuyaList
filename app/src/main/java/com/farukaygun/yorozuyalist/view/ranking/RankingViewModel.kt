package com.farukaygun.yorozuyalist.view.ranking

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.farukaygun.yorozuyalist.paging.RankingAnimePaging
import com.farukaygun.yorozuyalist.paging.RankingMangaPaging
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class RankingViewModel(application: Application) : BaseViewModel(application) {
    private val api = Api()

    private val rankingTypeFlow = MutableStateFlow("all")
    fun setRankingTypeFlow(value: String) {
        rankingTypeFlow.value = value
    }

    private val rankingAnimeFlow = Pager(
        PagingConfig(pageSize = 50, prefetchDistance = 50)
    ) {
        RankingAnimePaging(api, rankingTypeFlow.value)
    }.flow.cachedIn(viewModelScope)
    val rankingAnime = rankingAnimeFlow

    private val rankingMangaFlow = Pager(
        PagingConfig(pageSize = 50, prefetchDistance = 50)
    ) {
        RankingMangaPaging(api, rankingTypeFlow.value)
    }.flow.cachedIn(viewModelScope)
    val rankingManga = rankingMangaFlow
}
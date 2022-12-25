package com.farukaygun.yorozuyalist.view.ranking

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.farukaygun.yorozuyalist.paging.RankingAnimePaging
import com.farukaygun.yorozuyalist.paging.RankingMangaPaging
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.view.base.BaseViewModel

class RankingViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel(application) {
	private val api = Api()
	private val rankingType = savedStateHandle.get<String>("ranking_type") ?: "all"


	private val rankingAnimeFlow = Pager(
		PagingConfig(pageSize = 50, prefetchDistance = 50)
	) {
		RankingAnimePaging(api, rankingType)
	}.flow.cachedIn(viewModelScope)
	val rankingAnime = rankingAnimeFlow

	private val rankingMangaFlow = Pager(
		PagingConfig(pageSize = 50, prefetchDistance = 50)
	) {
		RankingMangaPaging(api, rankingType)
	}.flow.cachedIn(viewModelScope)
	val rankingManga = rankingMangaFlow
}
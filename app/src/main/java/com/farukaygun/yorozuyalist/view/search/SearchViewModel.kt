package com.farukaygun.yorozuyalist.view.search

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.farukaygun.yorozuyalist.paging.SearchAnimePaging
import com.farukaygun.yorozuyalist.paging.SearchMangaPaging
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SearchViewModel(application: Application) : BaseViewModel(application) {
	private val api = Api()

	private val queryFlow = MutableStateFlow("")
	val query = queryFlow

	var isNewSearch: Boolean = false

	fun setQuery(value: String) {
		queryFlow.value = value
		isNewSearch = true
	}

	private val animeSearchListFlow = Pager(
		PagingConfig(pageSize = 50, prefetchDistance = 50)
	) {
		SearchAnimePaging(api, queryFlow.value, isNewSearch)
	}.flow.cachedIn(viewModelScope)
	val animeSearchList = animeSearchListFlow

	private val mangaSearchListFlow = Pager(
		PagingConfig(pageSize = 50, prefetchDistance = 50)
	) {
		SearchMangaPaging(api, queryFlow.value, isNewSearch)
	}.flow.cachedIn(viewModelScope)
	val mangaSearchList = mangaSearchListFlow
}
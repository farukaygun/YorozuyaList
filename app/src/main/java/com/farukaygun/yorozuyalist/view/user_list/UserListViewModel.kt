package com.farukaygun.yorozuyalist.view.user_list

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.farukaygun.yorozuyalist.paging.UserAnimeListPaging
import com.farukaygun.yorozuyalist.paging.UserMangaListPaging
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class UserListViewModel(application: Application) : BaseViewModel(application) {
	private val api = Api()

	private val statusFlow = MutableStateFlow("completed")
	fun setStatusFlow(value: String) {
		statusFlow.value = value
	}

	private val userAnimeListFlow = Pager(
		PagingConfig(pageSize = 50, prefetchDistance = 50)
	) {
		UserAnimeListPaging(api, statusFlow.value)
	}.flow.cachedIn(viewModelScope)
	val userListAnime = userAnimeListFlow

	private val userMangaListFlow = Pager(
		PagingConfig(pageSize = 50, prefetchDistance = 50)
	) {
		UserMangaListPaging(api, statusFlow.value)
	}.flow.cachedIn(viewModelScope)
	val userListManga = userMangaListFlow
}
package com.farukaygun.yorozuyalist.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.service.Api

class SearchMangaPaging(
	private val api: Api,
	private val query: String,
	private var isNewSearch: Boolean
) : PagingSource<String, Data>() {

	override val keyReuseSupported: Boolean = true

	override fun getRefreshKey(state: PagingState<String, Data>): String? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)

			anchorPage?.prevKey ?: anchorPage?.nextKey
		}
	}

	override suspend fun load(params: LoadParams<String>): LoadResult<String, Data> {
		return try {
			val nextPage = params.key
			val response =
				if (nextPage == null) api.getSearchMangaList(query)
				else if (isNewSearch) {
					isNewSearch = false
					api.getSearchMangaList(query)
				} else api.getSearchMangaListPaging(nextPage)

			LoadResult.Page(
				data = response.data?.data!!,
				prevKey = response.data.paging.previous,
				nextKey = response.data.paging.next
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}

}
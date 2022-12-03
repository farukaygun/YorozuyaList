package com.farukaygun.yorozuyalist.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.service.Api

class UserMangaListPaging(
    private val api: Api,
    private val status: String
): PagingSource<String, Data>() {
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
                if (nextPage != null) api.getUserMangaListPaging(nextPage) else api.getUserMangaList(status)

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

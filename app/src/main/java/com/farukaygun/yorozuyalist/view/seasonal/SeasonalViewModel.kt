package com.farukaygun.yorozuyalist.view.seasonal

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.farukaygun.yorozuyalist.paging.SeasonalAnimePaging
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.util.Calendar as CalendarUtil
import com.farukaygun.yorozuyalist.view.base.BaseViewModel

class SeasonalViewModel(application: Application): BaseViewModel(application) {
    private val api = Api()

    private val seasonalAnimeFlow = Pager(
        PagingConfig(pageSize = 50, prefetchDistance = 50)
    ) {
        val (year, season) = CalendarUtil.getYearAndSeason()
        SeasonalAnimePaging(api, year, season.value)
    }.flow.cachedIn(viewModelScope)
    val seasonalAnime = seasonalAnimeFlow
}
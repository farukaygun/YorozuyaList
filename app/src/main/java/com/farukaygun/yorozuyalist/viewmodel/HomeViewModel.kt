package com.farukaygun.yorozuyalist.viewmodel

import android.app.Application
import android.icu.util.Calendar
import com.farukaygun.yorozuyalist.model.anime.SeasonalAnime
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.viewmodel.base.BaseViewModel
import kotlinx.coroutines.*
import com.farukaygun.yorozuyalist.util.Calendar as CalendarUtil
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate

class HomeViewModel(application: Application): BaseViewModel(application) {
    private val api = Api()

    private val seasonalAnimeListFlow = MutableStateFlow<ResponseHandler<SeasonalAnime>?>(null)
    val seasonalAnimeList = seasonalAnimeListFlow

    fun getSeasonalAnime() {
        val year: Int
        val month: CalendarUtil.Months
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            val date = LocalDate.now()
            year = date.year
            month = CalendarUtil.getMonth(date.month.value)!!
        } else {
            val calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = CalendarUtil.getMonth(calendar.get(Calendar.MONTH) + 1)!!
        }
        val season = CalendarUtil.getSeason(month)

        launch {
            seasonalAnimeListFlow.emit(ResponseHandler.Loading())
            api.getSeasonalAnime(year, season.value).let {
                seasonalAnimeListFlow.emit(it)
            }
        }
    }
}
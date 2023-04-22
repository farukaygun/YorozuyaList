package com.farukaygun.yorozuyalist.view.home

import android.app.Application
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.anime.SeasonalAnime
import com.farukaygun.yorozuyalist.model.anime.SuggestedAnime
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.util.Calendar
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.farukaygun.yorozuyalist.util.Calendar as CalendarUtil

class HomeViewModel(application: Application) : BaseViewModel(application) {
	private val api = Api()

	private val seasonalAnimeListFlow = MutableStateFlow<ResponseHandler<SeasonalAnime>?>(null)
	val seasonalAnimeList = seasonalAnimeListFlow

	private val suggestedAnimeListFlow = MutableStateFlow<ResponseHandler<SuggestedAnime>?>(null)
	val suggestedAnimeList = suggestedAnimeListFlow

	private val todayAnimeListFlow = MutableStateFlow<ResponseHandler<SeasonalAnime>?>(null)
	var todayAnimeList = MutableStateFlow<List<Data>?>(null)


	init {
		getSeasonalAnime()
		getTodayAnime()
		getSuggestedAnime()
	}

	private fun getTodayAnime() {
		val (year, season) = CalendarUtil.getYearAndSeason()
		val jpDayOfWeek = Calendar.currentJapanWeekday
		val tempList = mutableListOf<Data>()

		viewModelLaunch {
			todayAnimeListFlow.emit(ResponseHandler.Loading())
			api.getSeasonalAnime(year, season.value, 500).let {
				it.data?.data?.forEach { data ->
					if (data.node.broadcast?.dayOfTheWeek == jpDayOfWeek && data.node.status == "currently_airing")
						tempList.add(data)
				}
				todayAnimeList.emit(tempList)
			}
		}
	}

	private fun getSeasonalAnime() {
		val (year, season) = CalendarUtil.getYearAndSeason()
		viewModelLaunch {
			seasonalAnimeListFlow.emit(ResponseHandler.Loading())
			api.getSeasonalAnime(year, season.value, 10).let {
				seasonalAnimeListFlow.emit(it)
			}
		}
	}

	private fun getSuggestedAnime() {
		viewModelLaunch {
			suggestedAnimeListFlow.emit(ResponseHandler.Loading())
			api.getSuggestedAnime().let {
				suggestedAnimeListFlow.emit(it)
			}
		}
	}
}
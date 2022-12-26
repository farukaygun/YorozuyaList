package com.farukaygun.yorozuyalist.view.home

import android.app.Application
import com.farukaygun.yorozuyalist.model.anime.SeasonalAnime
import com.farukaygun.yorozuyalist.model.anime.SuggestedAnime
import com.farukaygun.yorozuyalist.service.Api
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseViewModel
import kotlinx.coroutines.flow.*
import com.farukaygun.yorozuyalist.util.Calendar as CalendarUtil

class HomeViewModel(application: Application) : BaseViewModel(application) {
	private val api = Api()

	private val seasonalAnimeListFlow = MutableStateFlow<ResponseHandler<SeasonalAnime>?>(null)
	val seasonalAnimeList = seasonalAnimeListFlow

	private val suggestedAnimeListFlow = MutableStateFlow<ResponseHandler<SuggestedAnime>?>(null)
	val suggestedAnimeList = suggestedAnimeListFlow


	init {
		getSeasonalAnime()
		getSuggestedAnime()
	}

	private fun getSeasonalAnime() {
		val (year, season) = CalendarUtil.getYearAndSeason()
		viewModelLaunch {
			seasonalAnimeListFlow.emit(ResponseHandler.Loading())
			api.getSeasonalAnime(year, season.value).let {
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
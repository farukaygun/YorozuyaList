package com.farukaygun.yorozuyalist.view.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearSnapHelper
import com.farukaygun.yorozuyalist.adapter.HomeAnimeAdapter
import com.farukaygun.yorozuyalist.adapter.TodayAnimeAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentHomeBinding
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
	private val viewModelHome: HomeViewModel by viewModels()
	override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
	override val isAppbarVisible: Boolean = true

	private lateinit var homeAnimeAdapter: HomeAnimeAdapter
	private lateinit var todayAnimeAdapter: TodayAnimeAdapter


	override fun start() {
		binding.buttonAnimeRanking.setOnClickListener {
			val action = HomeFragmentDirections.actionHomeFragmentToAnimeRankingFragment()
			Navigation.findNavController(it).navigate(action)
		}

		binding.buttonMangaRanking.setOnClickListener {
			val action = HomeFragmentDirections.actionHomeFragmentToMangaRankingFragment()
			Navigation.findNavController(it).navigate(action)
		}

		binding.textViewSeasonal.setOnClickListener {
			val action = HomeFragmentDirections.actionHomeFragmentToSeasonalFragment()
			Navigation.findNavController(it).navigate(action)
		}

		lifecycleLaunch {
			viewModelHome.todayAnimeList.collectLatest {
				it?.let {
					binding.progressBarToday.hide()
					todayAnimeAdapter = TodayAnimeAdapter(it)
					binding.recyclerViewTodayAnime.adapter = todayAnimeAdapter
				}
			}
		}

		lifecycleLaunch {
			viewModelHome.seasonalAnimeList.collectLatest {
				when (it) {
					is ResponseHandler.Loading -> binding.progressBarSeasonal.show()
					is ResponseHandler.Success -> {
						binding.progressBarSeasonal.hide()
						it.data?.let { seasonalAnime ->
							LinearSnapHelper().apply {
								attachToRecyclerView(binding.recyclerViewTodayAnime)
							}
							homeAnimeAdapter = HomeAnimeAdapter(seasonalAnime.data)
							binding.recyclerViewSeasonalAnime.adapter = homeAnimeAdapter
						}
					}

					is ResponseHandler.Error -> Toast.makeText(
						context,
						"${it.message}",
						Toast.LENGTH_SHORT
					).show()

					else -> {}
				}
			}
		}

		lifecycleLaunch {
			viewModelHome.suggestedAnimeList.collectLatest {
				when (it) {
					is ResponseHandler.Loading -> binding.progressBarSuggested.show()
					is ResponseHandler.Success -> {
						binding.progressBarSuggested.hide()
						it.data?.let { suggestedAnime ->
							homeAnimeAdapter = HomeAnimeAdapter(suggestedAnime.data)
							binding.recyclerViewSuggestedAnime.adapter = homeAnimeAdapter
						}
					}

					is ResponseHandler.Error -> Toast.makeText(
						context,
						"${it.message}",
						Toast.LENGTH_SHORT
					).show()

					else -> {}
				}
			}
		}
	}
}
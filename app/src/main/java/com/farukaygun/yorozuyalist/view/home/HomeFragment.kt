package com.farukaygun.yorozuyalist.view.home

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.farukaygun.yorozuyalist.adapter.HomeAnimeAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentHomeBinding
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModelHome: HomeViewModel by viewModels()
    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = true

    private lateinit var homeAnimeAdapter: HomeAnimeAdapter
    private lateinit var seasonalAnimeAdapter: HomeAnimeAdapter


    override fun start() {
        viewModelHome.getSeasonalAnime()

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
            viewModelHome.seasonalAnimeList.collectLatest {
                when(it) {
                    is ResponseHandler.Loading -> binding.progressBarSeasonal.visibility = View.VISIBLE
                    is ResponseHandler.Success -> {
                        binding.progressBarSeasonal.visibility = View.GONE
                        it.data?.let { seasonalAnime ->
                            homeAnimeAdapter = HomeAnimeAdapter(seasonalAnime.data)
                            binding.recyclerViewSeasonalAnime.adapter = homeAnimeAdapter
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }

        viewModelHome.getSuggestedAnime()

        lifecycleLaunch {
            viewModelHome.suggestedAnimeList.collectLatest {
                when(it) {
                    is ResponseHandler.Loading -> binding.progressBarSuggested.visibility = View.VISIBLE
                    is ResponseHandler.Success -> {
                        binding.progressBarSuggested.visibility = View.GONE
                        it.data?.let { suggestedAnime ->
                            seasonalAnimeAdapter = HomeAnimeAdapter(suggestedAnime.data)
                            binding.recyclerViewSuggestedAnime.adapter = seasonalAnimeAdapter
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
    }
}
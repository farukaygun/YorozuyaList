package com.farukaygun.yorozuyalist.view.home

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.farukaygun.yorozuyalist.adapter.SeasonalAnimeAdapter
import com.farukaygun.yorozuyalist.adapter.SuggestedAnimeAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentHomeBinding
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModelHome: HomeViewModel by viewModels()
    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = true

    private lateinit var seasonalAnimeAdapter: SeasonalAnimeAdapter
    private lateinit var suggestedAnimeAdapter: SuggestedAnimeAdapter


    override fun start() {
        viewModelHome.getSeasonalAnime()
        binding.recyclerViewSeasonalAnime.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.buttonAnimeRanking.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAnimeRankingFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.buttonMangaRanking.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToMangaRankingFragment()
            Navigation.findNavController(it).navigate(action)
        }

        lifecycleLaunch {
            viewModelHome.seasonalAnimeList.collectLatest {
                when(it) {
                    //is ResponseHandler.Loading -> binding.progressBarSeasonal.visibility = View.VISIBLE
                    is ResponseHandler.Success -> {
                        binding.progressBarSeasonal.visibility = View.GONE
                        it.data?.let { seasonalAnime ->
                            seasonalAnimeAdapter = SeasonalAnimeAdapter(seasonalAnime.data)
                            binding.recyclerViewSeasonalAnime.adapter = seasonalAnimeAdapter
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }

        viewModelHome.getSuggestedAnime()
        binding.recyclerViewSuggestedAnime.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        lifecycleLaunch {
            viewModelHome.suggestedAnimeList.collectLatest {
                when(it) {
                    //is ResponseHandler.Loading -> binding.progressBarSuggested.visibility = View.VISIBLE
                    is ResponseHandler.Success -> {
                        binding.progressBarSuggested.visibility = View.GONE
                        it.data?.let { suggestedAnime ->
                            suggestedAnimeAdapter = SuggestedAnimeAdapter(suggestedAnime.data)
                            binding.recyclerViewSuggestedAnime.adapter = suggestedAnimeAdapter
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
    }
}
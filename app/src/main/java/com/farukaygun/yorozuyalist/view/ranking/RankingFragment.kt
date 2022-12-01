package com.farukaygun.yorozuyalist.view.ranking

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.adapter.AnimeRankingAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentRankingBinding
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

/**
 * Viewpager content fragment.
 * Anime and Manga ranking fragments have same xml design.
 * Both classes uses this fragment for showing ranking list.
 */
class RankingFragment : BaseFragment<FragmentRankingBinding>() {
    val viewModelRanking: RankingViewModel by viewModels()
    override fun getViewBinding(): FragmentRankingBinding = FragmentRankingBinding.inflate(layoutInflater)
    // FIXME: Global appbar appears when false, does not appear when true. Working in reverse. May be about BaseRankingFragment.
    override val isAppbarVisible: Boolean = true

    override fun start() {
        val type = arguments?.getInt("rank_type")

        when(type) {
            0 -> {
                println("type 0")
                getAnimeRanking()
            }
            1 -> {
                println("type 1")
            }
        }
    }

    private fun getAnimeRanking() {
        val rankingType = arguments?.getString("ranking_type") ?: "all"
        println("ranking type: $rankingType")
        viewModelRanking.getAnimeRanking(rankingType)

        lifecycleLaunch {
            viewModelRanking.animeRanking.collectLatest {
                when(it) {
                    is ResponseHandler.Success -> {
                        println("success")
                        it.data?.let { animeRanking ->
                            val animeRankingAdapter = AnimeRankingAdapter(animeRanking.data)
                            binding.recyclerViewRanking.adapter = animeRankingAdapter
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
    }
}
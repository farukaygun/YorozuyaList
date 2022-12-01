package com.farukaygun.yorozuyalist.view.ranking

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.adapter.RankingAdapter
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
    private val viewModelRanking: RankingViewModel by viewModels()
    override fun getViewBinding(): FragmentRankingBinding = FragmentRankingBinding.inflate(layoutInflater)
    // FIXME: Global appbar appears when false, does not appear when true. Working in reverse. May be about BaseRankingFragment.
    override val isAppbarVisible: Boolean = true

    override fun start() {

        when(arguments?.getInt("type")) {
            0 -> getAnimeRanking()
            1 -> getMangaRanking()
        }
    }

    private fun getAnimeRanking() {
        val rankingType = arguments?.getString("ranking_type") ?: "all"
        viewModelRanking.getAnimeRanking(rankingType)

        lifecycleLaunch {
            viewModelRanking.animeRanking.collectLatest {
                when(it) {
                    is ResponseHandler.Loading -> binding.circularProgressBarRanking.visibility = View.VISIBLE
                    is ResponseHandler.Success -> {
                        binding.circularProgressBarRanking.visibility = View.GONE
                        it.data?.let { rankingAnime ->
                            val rankingAdapter = RankingAdapter(rankingAnime.data)
                            binding.recyclerViewRanking.adapter = rankingAdapter
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
    }

    private fun getMangaRanking() {
        val rankingType = arguments?.getString("ranking_type") ?: "all"
        viewModelRanking.getMangaRanking(rankingType)

        lifecycleLaunch {
            viewModelRanking.mangaRanking.collectLatest {
                when(it) {
                    is ResponseHandler.Loading -> binding.circularProgressBarRanking.visibility = View.VISIBLE
                    is ResponseHandler.Success -> {
                        binding.circularProgressBarRanking.visibility = View.GONE
                        it.data?.let { rankingManga ->
                            val mangaRankingAdapter = RankingAdapter(rankingManga.data)
                            binding.recyclerViewRanking.adapter = mangaRankingAdapter
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
    }
}
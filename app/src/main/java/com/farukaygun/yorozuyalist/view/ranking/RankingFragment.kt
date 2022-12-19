package com.farukaygun.yorozuyalist.view.ranking

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.farukaygun.yorozuyalist.adapter.RankingAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentRankingBinding
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

    private lateinit var rankingAnimeAdapter: RankingAdapter
    private lateinit var rankingMangaAdapter: RankingAdapter


    override fun start(savedInstanceState: Bundle?) {
        viewModelRanking.setRankingTypeFlow(arguments?.getString("ranking_type") ?: "all")
        when(arguments?.getInt("type")) {
            0 -> getAnimeRanking()
            1 -> getMangaRanking()
        }
    }

    private fun getAnimeRanking() {
        viewModelRanking.setRankingTypeFlow(arguments?.getString("ranking_type") ?: "all")

        rankingAnimeAdapter = RankingAdapter(0)
        binding.recyclerViewRanking.adapter = rankingAnimeAdapter

        lifecycleLaunch {
            rankingAnimeAdapter.loadStateFlow.collectLatest {
                if (it.refresh is LoadState.Loading) {
                    binding.circularProgressBar.show()
                } else {
                    binding.circularProgressBar.hide()
                }
            }
        }

        lifecycleLaunch {
            viewModelRanking.rankingAnime.collectLatest {
                rankingAnimeAdapter.submitData(it)
            }
        }
    }

    private fun getMangaRanking() {
        viewModelRanking.setRankingTypeFlow(arguments?.getString("ranking_type") ?: "all")

        rankingMangaAdapter = RankingAdapter(1)
        binding.recyclerViewRanking.adapter = rankingMangaAdapter

        lifecycleLaunch {
            rankingMangaAdapter.loadStateFlow.collectLatest {
                if (it.refresh is LoadState.Loading) {
                    binding.circularProgressBar.show()
                } else {
                    binding.circularProgressBar.hide()
                }
            }
        }

        lifecycleLaunch {
            viewModelRanking.rankingManga.collectLatest {
                rankingMangaAdapter.submitData(it)
            }
        }
    }
}
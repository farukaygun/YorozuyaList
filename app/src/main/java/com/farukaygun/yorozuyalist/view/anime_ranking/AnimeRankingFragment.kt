package com.farukaygun.yorozuyalist.view.anime_ranking

import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.databinding.FragmentAnimeRankingBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class AnimeRankingFragment : BaseFragment<FragmentAnimeRankingBinding>() {
    val animeRankingViewModel: AnimeRankingViewModel by viewModels()
    override fun getViewBinding(): FragmentAnimeRankingBinding = FragmentAnimeRankingBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = false


    override fun start() {
    }
}
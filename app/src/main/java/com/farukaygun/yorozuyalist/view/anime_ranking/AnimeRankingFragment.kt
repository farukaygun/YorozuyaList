package com.farukaygun.yorozuyalist.view.anime_ranking

import com.farukaygun.yorozuyalist.databinding.FragmentAnimeRankingBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment

class AnimeRankingFragment : BaseFragment<FragmentAnimeRankingBinding>() {
    override fun getViewBinding(): FragmentAnimeRankingBinding = FragmentAnimeRankingBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = false


    override fun start() {
    }
}
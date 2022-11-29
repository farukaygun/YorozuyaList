package com.farukaygun.yorozuyalist.view.manga_ranking

import com.farukaygun.yorozuyalist.databinding.FragmentMangaRankingBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment

class MangaRankingFragment : BaseFragment<FragmentMangaRankingBinding>() {
    override fun getViewBinding(): FragmentMangaRankingBinding = FragmentMangaRankingBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = false


    override fun start() {
    }
}
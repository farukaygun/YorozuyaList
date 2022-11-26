package com.farukaygun.yorozuyalist.view.anime

import com.farukaygun.yorozuyalist.databinding.FragmentAnimeBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment

class AnimeFragment : BaseFragment<FragmentAnimeBinding>() {
    override fun getViewBinding(): FragmentAnimeBinding = FragmentAnimeBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = true

    override fun start() {
    }
}
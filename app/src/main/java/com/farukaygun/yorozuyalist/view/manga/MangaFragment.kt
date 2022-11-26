package com.farukaygun.yorozuyalist.view.manga

import com.farukaygun.yorozuyalist.databinding.FragmentMangaBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment

class MangaFragment : BaseFragment<FragmentMangaBinding>() {
    override val isAppbarVisible: Boolean = true

    override fun getViewBinding(): FragmentMangaBinding = FragmentMangaBinding.inflate(layoutInflater)


    override fun start() {
    }
}
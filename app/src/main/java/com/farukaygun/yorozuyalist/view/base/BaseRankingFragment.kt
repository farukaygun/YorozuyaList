package com.farukaygun.yorozuyalist.view.base

import com.farukaygun.yorozuyalist.adapter.viewpager.AnimeRankingViewPagerAdapter
import com.farukaygun.yorozuyalist.adapter.viewpager.MangaRankingViewPagerAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentBaseRankingBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Base ViewPager fragment for Anime and Manga Ranking fragments.
 * arg type 0 is anime ranking, 1 is manga ranking.
 * Both fragments uses Ranking Fragment for showing ranking list in viewPager.
 */
class BaseRankingFragment : BaseFragment<FragmentBaseRankingBinding>() {
    override fun getViewBinding(): FragmentBaseRankingBinding = FragmentBaseRankingBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = false

    override fun start() {
        val type = arguments?.let { BaseRankingFragmentArgs.fromBundle(it).type } ?: 0
        binding.toolBar.setNavigationOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() } // back

        when(type) {
            0 -> binding.viewPager.adapter = AnimeRankingViewPagerAdapter(childFragmentManager, lifecycle)
            1 -> binding.viewPager.adapter = MangaRankingViewPagerAdapter(childFragmentManager, lifecycle)
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "Score"
                1 -> tab.text = "Popular"
                2 -> tab.text = "Favorite"
                3 -> tab.text = "Upcoming"
            }
        }.attach()
    }
}
package com.farukaygun.yorozuyalist.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.farukaygun.yorozuyalist.view.ranking.RankingFragment

class MangaRankingViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = RankingFragment()
        val bundle = Bundle()

        when (position) {
            0 -> bundle.putString("ranking_type", "all")
            1 -> bundle.putString("ranking_type", "bypopularity")
            2 -> bundle.putString("ranking_type", "favorite")
        }

        bundle.putInt("type", 1)
        fragment.arguments = bundle
        return fragment
    }
}
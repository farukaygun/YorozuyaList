package com.farukaygun.yorozuyalist.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.farukaygun.yorozuyalist.view.ranking.RankingFragment

class AnimeRankingViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
	: FragmentStateAdapter(fragmentManager, lifecycle) {
	override fun getItemCount(): Int = 4

	override fun createFragment(position: Int): Fragment {
		val fragment = RankingFragment()
		val bundle = Bundle()

		// api query parameters
		when (position) {
			0 -> bundle.putString("ranking_type", "all")
			1 -> bundle.putString("ranking_type", "bypopularity")
			2 -> bundle.putString("ranking_type", "favorite")
			3 -> bundle.putString("ranking_type", "upcoming")
		}

		bundle.putInt("type", 0)
		fragment.arguments = bundle
		return fragment
	}


}
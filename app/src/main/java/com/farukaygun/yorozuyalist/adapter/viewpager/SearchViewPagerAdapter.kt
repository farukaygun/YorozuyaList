package com.farukaygun.yorozuyalist.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.farukaygun.yorozuyalist.view.search.SearchFragment

class SearchViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
	FragmentStateAdapter(fragmentManager, lifecycle) {
	override fun getItemCount(): Int = 2

	override fun createFragment(position: Int): Fragment {
		val fragment = SearchFragment()
		val bundle = Bundle()

		when (position) {
			0 -> bundle.putInt("type", 0)
			1 -> bundle.putInt("type", 1)
		}
		fragment.arguments = bundle
		return fragment
	}

}
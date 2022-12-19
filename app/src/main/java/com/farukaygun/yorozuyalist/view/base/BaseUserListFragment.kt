package com.farukaygun.yorozuyalist.view.base

import android.os.Bundle
import com.farukaygun.yorozuyalist.adapter.viewpager.UserAnimeListViewPagerAdapter
import com.farukaygun.yorozuyalist.adapter.viewpager.UserMangaListViewPagerAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentBaseUserListBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Base ViewPager fragment for Anime and Manga fragments.
 * arg type 0 is user anime list, 1 is user manga list.
 * Both fragments uses User List Fragment for showing user list in viewPager.
 */
class BaseUserListFragment : BaseFragment<FragmentBaseUserListBinding>() {
    override fun getViewBinding(): FragmentBaseUserListBinding = FragmentBaseUserListBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = true

    override fun start(savedInstanceState: Bundle?) {
        val type = arguments?.let { BaseUserListFragmentArgs.fromBundle(it).type } ?: 0

        when(type) {
            0 -> binding.viewPager.adapter = UserAnimeListViewPagerAdapter(childFragmentManager, lifecycle)
            1 -> binding.viewPager.adapter = UserMangaListViewPagerAdapter(childFragmentManager, lifecycle)
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = if(type == 0) "Watching" else "Reading"
                1 -> tab.text = if(type == 0) "Plan to Watch" else "Plan to Read"
                2 -> tab.text = "Completed"
                3 -> tab.text = "On Hold"
                4 -> tab.text = "Dropped"
            }
        }.attach()
    }
}
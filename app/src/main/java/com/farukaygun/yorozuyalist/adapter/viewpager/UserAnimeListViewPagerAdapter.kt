package com.farukaygun.yorozuyalist.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.farukaygun.yorozuyalist.view.user_list.UserListFragment

class UserAnimeListViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        val fragment = UserListFragment()
        val bundle = Bundle()

        when (position) {
            0 -> bundle.putString("status", "watching")
            1 -> bundle.putString("status", "plan_to_watch")
            2 -> bundle.putString("status", "completed")
            3 -> bundle.putString("status", "on_hold")
            4 -> bundle.putString("status", "dropped")
        }

        bundle.putInt("type", 0)
        fragment.arguments = bundle
        return fragment
    }
}

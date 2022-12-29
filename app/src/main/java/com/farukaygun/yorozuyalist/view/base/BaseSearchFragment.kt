package com.farukaygun.yorozuyalist.view.base

import androidx.appcompat.widget.SearchView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.adapter.viewpager.SearchViewPagerAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentBaseSearchBinding
import com.farukaygun.yorozuyalist.util.hideKeyboard
import com.farukaygun.yorozuyalist.view.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator

class BaseSearchFragment : BaseFragment<FragmentBaseSearchBinding>() {
	override val isAppbarVisible: Boolean = false
	override fun getViewBinding(): FragmentBaseSearchBinding =
		FragmentBaseSearchBinding.inflate(layoutInflater)


	override fun start() {
		binding.toolBar.setNavigationOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() } // back

		binding.viewPager.adapter = SearchViewPagerAdapter(childFragmentManager, lifecycle)

		TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
			when (position) {
				0 -> tab.text = getString(R.string.anime)
				1 -> tab.text = getString(R.string.manga)
			}
		}.attach()

		binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				if (!query.isNullOrBlank()) {
					(childFragmentManager.findFragmentByTag("f${binding.viewPager.currentItem}") as SearchFragment)
						.search(query)
					view?.hideKeyboard()
				}
				return true
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				return true
			}
		})
	}

}
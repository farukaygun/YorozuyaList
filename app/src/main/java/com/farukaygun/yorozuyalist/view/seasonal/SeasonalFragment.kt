package com.farukaygun.yorozuyalist.view.seasonal

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.farukaygun.yorozuyalist.adapter.SeasonalAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentSeasonalBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

class SeasonalFragment : BaseFragment<FragmentSeasonalBinding>() {
	private val viewModelSeasonal: SeasonalViewModel by viewModels()
	override val isAppbarVisible: Boolean = false
	override fun getViewBinding(): FragmentSeasonalBinding =
		FragmentSeasonalBinding.inflate(layoutInflater)


	override fun start() {
		binding.toolBar.setNavigationOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() } // back

		val seasonalAdapter = SeasonalAdapter()
		binding.recyclerViewSeasonal.adapter = seasonalAdapter

		lifecycleLaunch {
			seasonalAdapter.loadStateFlow.collectLatest {
				if (it.refresh is LoadState.Loading) {
					binding.circularProgressBar.show()
				} else {
					binding.circularProgressBar.hide()
				}
			}
		}

		lifecycleLaunch {
			viewModelSeasonal.seasonalAnime.collectLatest {
				seasonalAdapter.submitData(it)
			}
		}
	}

}
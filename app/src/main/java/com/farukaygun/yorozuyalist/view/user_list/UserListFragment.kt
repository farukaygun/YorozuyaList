package com.farukaygun.yorozuyalist.view.user_list

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.farukaygun.yorozuyalist.adapter.UserListAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentUserListBinding
import com.farukaygun.yorozuyalist.util.Constants.STATUS_ALL
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

class UserListFragment : BaseFragment<FragmentUserListBinding>() {
	private val viewModelUserList: UserListViewModel by viewModels()
	override fun getViewBinding(): FragmentUserListBinding =
		FragmentUserListBinding.inflate(layoutInflater)

	override val isAppbarVisible: Boolean = true

	private lateinit var adapter: UserListAdapter

	private val type: Int by lazy {
		arguments?.getInt("type") ?: 0
	}

	override fun start() {
		when (type) {
			0 -> getUserAnimeList()
			1 -> getUserMangaList()
		}

		binding.swipeRefreshLayout.setOnRefreshListener {
			binding.swipeRefreshLayout.isRefreshing = false
			adapter.refresh()
		}
	}

	private fun getUserAnimeList() {
		viewModelUserList.setStatusFlow(arguments?.getString("status") ?: STATUS_ALL)

		adapter = UserListAdapter(0)
		binding.recyclerViewUserList.adapter = adapter

		lifecycleLaunch {
			adapter.loadStateFlow.collectLatest {
				if (it.refresh is LoadState.Loading) {
					binding.circularProgressBar.show()
				} else {
					binding.circularProgressBar.hide()
				}
			}
		}

		lifecycleLaunch {
			viewModelUserList.userListAnime.collectLatest {
				adapter.submitData(it)
			}
		}
	}

	private fun getUserMangaList() {
		viewModelUserList.setStatusFlow(arguments?.getString("status") ?: STATUS_ALL)

		adapter = UserListAdapter(1)
		binding.recyclerViewUserList.adapter = adapter

		lifecycleLaunch {
			adapter.loadStateFlow.collectLatest {
				if (it.refresh is LoadState.Loading) {
					binding.circularProgressBar.show()
				} else {
					binding.circularProgressBar.hide()
				}
			}
		}

		lifecycleLaunch {
			viewModelUserList.userListManga.collectLatest {
				adapter.submitData(it)
			}
		}
	}
}
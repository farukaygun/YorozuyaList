package com.farukaygun.yorozuyalist.view.user_list

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.farukaygun.yorozuyalist.adapter.UserListAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentUserListBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

class UserListFragment : BaseFragment<FragmentUserListBinding>() {
    private val viewModelUserList: UserListViewModel by viewModels()
    override fun getViewBinding(): FragmentUserListBinding = FragmentUserListBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = true

    private lateinit var userAnimeListAdapter: UserListAdapter
    private lateinit var userMangaListAdapter: UserListAdapter

    override fun start() {
        when(arguments?.getInt("type")) {
            0 -> getUserAnimeList()
            1 -> getUserMangaList()
        }
    }

    private fun getUserAnimeList() {
        viewModelUserList.setStatusFlow(arguments?.getString("status") ?: "all")

        userAnimeListAdapter = UserListAdapter()
        binding.recyclerViewUserList.adapter = userAnimeListAdapter

        lifecycleLaunch {
            userAnimeListAdapter.loadStateFlow.collectLatest {
                if (it.refresh is LoadState.Loading) {
                    binding.circularProgressBar.show()
                } else {
                    binding.circularProgressBar.hide()
                }
            }
        }

        lifecycleLaunch {
            viewModelUserList.userListAnime.collectLatest {
                userAnimeListAdapter.submitData(it)
            }
        }
    }

    private fun getUserMangaList() {
        viewModelUserList.setStatusFlow(arguments?.getString("status") ?: "all")

        userMangaListAdapter = UserListAdapter()
        binding.recyclerViewUserList.adapter = userMangaListAdapter

        lifecycleLaunch {
            userMangaListAdapter.loadStateFlow.collectLatest {
                if (it.refresh is LoadState.Loading) {
                    binding.circularProgressBar.show()
                } else {
                    binding.circularProgressBar.hide()
                }
            }
        }

        lifecycleLaunch {
            viewModelUserList.userListManga.collectLatest {
                userMangaListAdapter.submitData(it)
            }
        }
    }
}
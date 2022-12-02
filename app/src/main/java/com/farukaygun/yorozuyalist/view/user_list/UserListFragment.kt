package com.farukaygun.yorozuyalist.view.user_list

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.farukaygun.yorozuyalist.adapter.UserListAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentUserListBinding
import com.farukaygun.yorozuyalist.service.ResponseHandler
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

class UserListFragment : BaseFragment<FragmentUserListBinding>() {
    private val viewModelUserList: UserListViewModel by viewModels()
    override fun getViewBinding(): FragmentUserListBinding = FragmentUserListBinding.inflate(layoutInflater)
    override val isAppbarVisible: Boolean = true

    override fun start() {
        when(arguments?.getInt("type")) {
            0 -> getUserAnimeList()
            1 -> getUserMangaList()
        }
    }

    private fun getUserAnimeList() {
        val status = arguments?.getString("status") ?: "all"
        viewModelUserList.getUserAnimeList(status)

        lifecycleLaunch {
            viewModelUserList.userAnimeList.collectLatest {
                when(it) {
                    is ResponseHandler.Loading -> binding.circularProgressBar.visibility = View.VISIBLE
                    is ResponseHandler.Success -> {
                        binding.circularProgressBar.visibility = View.GONE
                        it.data?.let { userAnimeList ->
                            val userAnimeListAdapter = UserListAdapter(userAnimeList.data)
                            binding.recyclerViewUserList.adapter = userAnimeListAdapter
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
    }

    private fun getUserMangaList() {
        val status = arguments?.getString("status") ?: "all"
        viewModelUserList.getUserMangaList(status)

        lifecycleLaunch {
            viewModelUserList.userMangaList.collectLatest {
                when(it) {
                    is ResponseHandler.Loading -> binding.circularProgressBar.visibility = View.VISIBLE
                    is ResponseHandler.Success -> {
                        binding.circularProgressBar.visibility = View.GONE
                        it.data?.let { userMangaList ->
                            val userMangaListAdapter = UserListAdapter(userMangaList.data)
                            binding.recyclerViewUserList.adapter = userMangaListAdapter
                        }
                    }
                    is ResponseHandler.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            }
        }
    }
}
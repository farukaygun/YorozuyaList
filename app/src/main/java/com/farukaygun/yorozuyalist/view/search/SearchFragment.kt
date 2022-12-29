package com.farukaygun.yorozuyalist.view.search

import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.farukaygun.yorozuyalist.adapter.SearchAdapter
import com.farukaygun.yorozuyalist.databinding.FragmentSearchBinding
import com.farukaygun.yorozuyalist.view.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
	private val viewModelSearch: SearchViewModel by viewModels()
	override val isAppbarVisible: Boolean = false
	override fun getViewBinding(): FragmentSearchBinding = FragmentSearchBinding.inflate(layoutInflater)

	private lateinit var searchAnimeAdapter: SearchAdapter
	private lateinit var searchMangaAdapter: SearchAdapter

	private val type: Int by lazy {
		arguments?.getInt("type") ?: 0
	}


	override fun start() {
		when (type) {
			0 -> animeSearch()
			1 -> mangaSearch()
		}
	}

	private fun animeSearch() {
		searchAnimeAdapter = SearchAdapter(0)
		binding.recyclerViewSearch.adapter = searchAnimeAdapter

		// paging
		lifecycleLaunch {
			searchAnimeAdapter.loadStateFlow.collectLatest {
				if (it.refresh is LoadState.Loading) {
					binding.circularProgressBar.show()
				} else {
					binding.circularProgressBar.hide()
				}
			}
		}

		// search query
		lifecycleLaunch {
			viewModelSearch.query.collectLatest { q ->
				if (q.isNotBlank()) {
					lifecycleLaunch {
						viewModelSearch.animeSearchList.collectLatest {
							searchAnimeAdapter.submitData(it)
						}
					}
				}
			}
		}
	}

	private fun mangaSearch() {
		searchMangaAdapter = SearchAdapter(1)
		binding.recyclerViewSearch.adapter = searchMangaAdapter

		// paging
		lifecycleLaunch {
			searchAnimeAdapter.loadStateFlow.collectLatest {
				if (it.refresh is LoadState.Loading) {
					binding.circularProgressBar.show()
				} else {
					binding.circularProgressBar.hide()
				}
			}
		}

		// search query
		lifecycleLaunch {
			viewModelSearch.query.collectLatest { q ->
				if (q.isNotBlank()) {
					lifecycleLaunch {
						viewModelSearch.mangaSearchList.collectLatest {
							searchMangaAdapter.submitData(it)
						}
					}
				}
			}
		}
	}

	fun search(query: String) {
		if (query.isNotBlank()) {
			viewModelSearch.setQuery(query)
			when (type) {
				0 -> searchAnimeAdapter.refresh()
				1 -> searchMangaAdapter.refresh()
			}
		}
	}
}
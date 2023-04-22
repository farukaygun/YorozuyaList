package com.farukaygun.yorozuyalist.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemSearchRecyclerBinding
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node

class SearchAdapter(val type: Int) :
	PagingDataAdapter<Data, SearchAdapter.ViewHolder>(SearchComparator), IItemClickListener {

	class ViewHolder(val binding: ItemSearchRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val binding = DataBindingUtil.inflate<ItemSearchRecyclerBinding>(
			LayoutInflater.from(parent.context),
			R.layout.item_search_recycler,
			parent,
			false
		)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		getItem(position)?.let {
			holder.binding.searchListData = it
			holder.binding.listener = this
		}
	}

	override fun onItemClicked(view: View, data: Node) {
		val bundle = Bundle()
		bundle.putInt("id", data.id)
		when (type) {
			0 -> Navigation.findNavController(view)
				.navigate(R.id.action_baseSearchFragment_to_animeDetailsFragment, bundle)

			1 -> Navigation.findNavController(view)
				.navigate(R.id.action_baseSearchFragment_to_mangaDetailsFragment, bundle)
		}
	}

	object SearchComparator : DiffUtil.ItemCallback<Data>() {
		override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
			return oldItem.node.id == newItem.node.id
		}

		override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
			return oldItem == newItem
		}
	}
}
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
import com.farukaygun.yorozuyalist.databinding.ItemSeasonalAnimeRecyclerBinding
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node

class SeasonalAdapter
    : PagingDataAdapter<Data, SeasonalAdapter.ViewHolder>(SeasonalAdapter.SeasonalComparator), IItemClickListener {

    class ViewHolder(val binding: ItemSeasonalAnimeRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemSeasonalAnimeRecyclerBinding>(LayoutInflater.from(parent.context), R.layout.item_seasonal_anime_recycler, parent, false)
        return SeasonalAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding.animeData = it.node
            holder.binding.listener = this
        }
    }

    override fun onItemClicked(view: View, data: Node) {
        val bundle = Bundle()
        bundle.putInt("id", data.id)
        Navigation.findNavController(view).navigate(R.id.action_seasonalFragment_to_detailsFragment, bundle)
    }

    object SeasonalComparator: DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.node.id == newItem.node.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
}
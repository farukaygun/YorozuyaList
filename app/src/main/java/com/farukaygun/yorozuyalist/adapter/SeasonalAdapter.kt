package com.farukaygun.yorozuyalist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemSeasonalAnimeRecyclerBinding
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node

class SeasonalAdapter()
    : PagingDataAdapter<Data, SeasonalAdapter.ViewHolder>(SeasonalAdapter.SeasonalComparator), ISeasonalAnimeClickListener {

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

    override fun onSeasonalAnimeClicked(view: View, seasonalAnimeData: Node) {
        Toast.makeText(view.context, "${seasonalAnimeData.title} ", Toast.LENGTH_SHORT).show()
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
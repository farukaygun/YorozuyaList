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
import com.farukaygun.yorozuyalist.databinding.ItemRankingRecyclerBinding
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node

class RankingAdapter()
    : PagingDataAdapter<Data, RankingAdapter.ViewHolder>(RankingComparator), IRankingClickListener {

    class ViewHolder(val binding: ItemRankingRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRankingRecyclerBinding>(LayoutInflater.from(parent.context), R.layout.item_ranking_recycler, parent, false)
        return RankingAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding.rankingData = it.node
            holder.binding.listener = this
        }
    }

    override fun onRankingClicked(view: View, rankingData: Node) {
        Toast.makeText(view.context, "${rankingData.title} ", Toast.LENGTH_SHORT).show()
    }

    object RankingComparator: DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.node.id == newItem.node.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
}
package com.farukaygun.yorozuyalist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemRankingRecyclerBinding
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node

class RankingAdapter(private val animeRankingList: List<Data>)
    : RecyclerView.Adapter<RankingAdapter.ViewHolder>(), IRankingClickListener {

    class ViewHolder(val binding: ItemRankingRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRankingRecyclerBinding>(LayoutInflater.from(parent.context), R.layout.item_ranking_recycler, parent, false)
        return RankingAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.rankingData = animeRankingList[position].node
        holder.binding.listener = this
    }

    override fun getItemCount(): Int {
        return animeRankingList.size
    }

    override fun onRankingClicked(view: View, rankingData: Node) {
        Toast.makeText(view.context, "${rankingData.title} ", Toast.LENGTH_SHORT).show()
    }
}
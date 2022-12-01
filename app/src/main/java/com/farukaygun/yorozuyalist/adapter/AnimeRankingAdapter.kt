package com.farukaygun.yorozuyalist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemRankingRecyclerBinding
import com.farukaygun.yorozuyalist.model.anime.AnimeData
import com.farukaygun.yorozuyalist.model.anime.Node

class AnimeRankingAdapter(private val animeRankingList: List<AnimeData>)
    : RecyclerView.Adapter<AnimeRankingAdapter.ViewHolder>(), IRankingAnimeClickListener {

    class ViewHolder(val binding: ItemRankingRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRankingRecyclerBinding>(LayoutInflater.from(parent.context), R.layout.item_ranking_recycler, parent, false)
        return AnimeRankingAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.rankingAnimeData = animeRankingList[position].node
        holder.binding.listener = this
    }

    override fun getItemCount(): Int {
        return animeRankingList.size
    }

    override fun onRankingAnimeClicked(view: View, rankingAnimeData: Node) {
        Toast.makeText(view.context, "${rankingAnimeData.title} ", Toast.LENGTH_SHORT).show()
    }
}
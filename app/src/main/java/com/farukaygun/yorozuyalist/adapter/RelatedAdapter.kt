package com.farukaygun.yorozuyalist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemHomeAnimeRecyclerBinding
import com.farukaygun.yorozuyalist.model.anime.RelatedAnime

class RelatedAdapter(private val relatedAnimeList: List<RelatedAnime>) : RecyclerView.Adapter<RelatedAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemHomeAnimeRecyclerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeAnimeRecyclerBinding>(LayoutInflater.from(parent.context), R.layout.item_home_anime_recycler, parent, false)
        return RelatedAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.animeData = relatedAnimeList[position].node
        //holder.binding.listener = this
    }

    override fun getItemCount(): Int {
        return relatedAnimeList.size
    }
}
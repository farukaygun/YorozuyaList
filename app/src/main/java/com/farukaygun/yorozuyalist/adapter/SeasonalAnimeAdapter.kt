package com.farukaygun.yorozuyalist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemSeasonalAnimeRecyclerBinding
import com.farukaygun.yorozuyalist.model.anime.AnimeData
import com.farukaygun.yorozuyalist.model.anime.AnimeNode

class SeasonalAnimeAdapter(private var seasonalAnimeList: List<AnimeData>)
    : RecyclerView.Adapter<SeasonalAnimeAdapter.ViewHolder>(), ISeasonalAnimeClickListener {

    class ViewHolder(val binding: ItemSeasonalAnimeRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemSeasonalAnimeRecyclerBinding>(LayoutInflater.from(parent.context), R.layout.item_seasonal_anime_recycler, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.seasonalAnimeData = seasonalAnimeList[position].node
        holder.binding.listener = this
    }

    override fun getItemCount(): Int {
        return seasonalAnimeList.size
    }

    override fun onSeasonalAnimeClicked(view: View, seasonalAnimeData: AnimeNode) {
        Toast.makeText(view.context, "${seasonalAnimeData.title} ", Toast.LENGTH_SHORT).show()
    }
}
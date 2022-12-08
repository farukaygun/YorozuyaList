package com.farukaygun.yorozuyalist.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemHomeAnimeRecyclerBinding
import com.farukaygun.yorozuyalist.model.Node
import com.farukaygun.yorozuyalist.model.Related
import com.farukaygun.yorozuyalist.view.details.anime.AnimeDetailsFragmentDirections
import com.farukaygun.yorozuyalist.view.details.manga.MangaDetailsFragmentDirections

class RelatedAdapter(val type: Int, private val relatedList: List<Related>)
    : RecyclerView.Adapter<RelatedAdapter.ViewHolder>(), IItemClickListener {

    class ViewHolder(val binding: ItemHomeAnimeRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemHomeAnimeRecyclerBinding>(LayoutInflater.from(parent.context), R.layout.item_home_anime_recycler, parent, false)
        return RelatedAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.animeData = relatedList[position].node
        holder.binding.listener = this
    }

    override fun getItemCount(): Int {
        return relatedList.size
    }

    override fun onItemClicked(view: View, data: Node) {
        val bundle = Bundle()
        bundle.putInt("id", data.id)
        when(type) {
            0 -> Navigation.findNavController(view).navigate(AnimeDetailsFragmentDirections.actionAnimeDetailsFragmentSelf().actionId, bundle)
            1 -> Navigation.findNavController(view).navigate(MangaDetailsFragmentDirections.actionMangaDetailsFragmentSelf().actionId, bundle)
        }
    }
}
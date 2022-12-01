package com.farukaygun.yorozuyalist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemSuggestedAnimeRecyclerBinding
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node

class SuggestedAnimeAdapter(private val suggestedAnimeList: List<Data>)
    : RecyclerView.Adapter<SuggestedAnimeAdapter.ViewHolder>(), ISuggestedAnimeClickListener {

    class ViewHolder(val binding: ItemSuggestedAnimeRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemSuggestedAnimeRecyclerBinding>(LayoutInflater.from(parent.context), R.layout.item_suggested_anime_recycler, parent, false)
        return SuggestedAnimeAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.suggestedAnimeData = suggestedAnimeList[position].node
        holder.binding.listener = this
    }

    override fun getItemCount(): Int {
        return suggestedAnimeList.size
    }

    override fun onSuggestedAnimeClicked(view: View, suggestedAnimeData: Node) {
        Toast.makeText(view.context, "${suggestedAnimeData.title} ", Toast.LENGTH_SHORT).show()
    }
}
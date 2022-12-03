package com.farukaygun.yorozuyalist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemHomeAnimeRecyclerBinding
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node

/**
 * Suggested and seasonal anime adapter in home fragment
 */
class HomeAnimeAdapter(private var animeList: List<Data>)
    : RecyclerView.Adapter<HomeAnimeAdapter.ViewHolder>(), IHomeAnimeClickListener {

    class ViewHolder(val binding: ItemHomeAnimeRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemHomeAnimeRecyclerBinding>(LayoutInflater.from(parent.context),
                R.layout.item_home_anime_recycler,
                parent,
                false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.animeData = animeList[position].node
        holder.binding.listener = this
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    override fun onHomeAnimeClicked(view: View, homeAnimeData: Node) {
        Toast.makeText(view.context, "${homeAnimeData.title} ", Toast.LENGTH_SHORT).show()
    }
}
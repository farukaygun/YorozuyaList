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
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node
import com.farukaygun.yorozuyalist.view.home.HomeFragmentDirections

/**
 * Suggested and seasonal anime adapter in home fragment
 */
class HomeAnimeAdapter(private var animeList: List<Data>)
    : RecyclerView.Adapter<HomeAnimeAdapter.ViewHolder>(), IItemClickListener {

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

    override fun onItemClicked(view: View, data: Node) {
        val bundle = Bundle()
        bundle.putInt("id", data.id)
        Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment().actionId, bundle)
    }
}
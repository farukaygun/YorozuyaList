package com.farukaygun.yorozuyalist.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemUserListRecyclerBinding
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node

class UserListAdapter(val type: Int)
    : PagingDataAdapter<Data, UserListAdapter.ViewHolder>(UserListComparator), IItemClickListener {

    class ViewHolder(val binding: ItemUserListRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserListRecyclerBinding>(LayoutInflater.from(parent.context), R.layout.item_user_list_recycler, parent, false)
        return UserListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding.userListData = it
            holder.binding.listener = this
        }
    }

    override fun onItemClicked(view: View, data: Node) {
        val bundle = Bundle()
        bundle.putInt("id", data.id)
        when(type) {
            0 -> Navigation.findNavController(view).navigate(R.id.action_animeFragment_to_animeDetailsFragment, bundle)
            1 -> Navigation.findNavController(view).navigate(R.id.action_mangaFragment_to_mangaDetailsFragment, bundle)
        }
    }

    object UserListComparator: DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.node.id == newItem.node.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
}
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
import com.farukaygun.yorozuyalist.databinding.ItemUserListRecyclerBinding
import com.farukaygun.yorozuyalist.model.Data

class UserListAdapter()
    : PagingDataAdapter<Data, UserListAdapter.ViewHolder>(UserListComparator), IUserListClickListener {

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

    override fun onUserListClicked(view: View, userListData: Data) {
        Toast.makeText(view.context, "${userListData.node.title} ", Toast.LENGTH_SHORT).show()
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
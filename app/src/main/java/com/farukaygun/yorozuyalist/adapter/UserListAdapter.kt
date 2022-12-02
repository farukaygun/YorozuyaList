package com.farukaygun.yorozuyalist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farukaygun.yorozuyalist.R
import com.farukaygun.yorozuyalist.databinding.ItemUserListRecyclerBinding
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node

class UserListAdapter(private val userList: List<Data>)
    : RecyclerView.Adapter<UserListAdapter.ViewHolder>(), IUserListClickListener {

    class ViewHolder(val binding: ItemUserListRecyclerBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserListRecyclerBinding>(LayoutInflater.from(parent.context), R.layout.item_user_list_recycler, parent, false)
        return UserListAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.userListData = userList[position]
        holder.binding.listener = this
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onUserListClicked(view: View, userListData: Data) {
        Toast.makeText(view.context, "${userListData.node.title} ", Toast.LENGTH_SHORT).show()
    }
}
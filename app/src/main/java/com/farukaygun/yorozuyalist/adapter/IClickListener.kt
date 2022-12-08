package com.farukaygun.yorozuyalist.adapter

import android.view.View
import com.farukaygun.yorozuyalist.model.Node

interface IItemClickListener {
    fun onItemClicked(view: View, data: Node)
}
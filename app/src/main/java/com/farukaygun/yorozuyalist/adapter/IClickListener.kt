package com.farukaygun.yorozuyalist.adapter

import android.view.View
import com.farukaygun.yorozuyalist.model.Data
import com.farukaygun.yorozuyalist.model.Node

interface ISeasonalAnimeClickListener {
    fun onSeasonalAnimeClicked(view: View, seasonalAnimeData: Node)
}

interface IHomeAnimeClickListener {
    fun onHomeAnimeClicked(view: View, homeAnimeData: Node)
}

interface IRankingClickListener {
    fun onRankingClicked(view: View, rankingData: Node)
}

interface IUserListClickListener {
    fun onUserListClicked(view: View, userListData: Data)
}
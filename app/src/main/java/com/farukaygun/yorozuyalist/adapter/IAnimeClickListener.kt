package com.farukaygun.yorozuyalist.adapter

import android.view.View
import com.farukaygun.yorozuyalist.model.anime.Node

interface ISeasonalAnimeClickListener {
    fun onSeasonalAnimeClicked(view: View, seasonalAnimeData: Node)
}

interface ISuggestedAnimeClickListener {
    fun onSuggestedAnimeClicked(view: View, suggestedAnimeData: Node)
}

interface IRankingAnimeClickListener {
    fun onRankingAnimeClicked(view: View, rankingAnimeData: Node)
}
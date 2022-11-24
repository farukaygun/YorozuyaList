package com.farukaygun.yorozuyalist.adapter

import android.view.View
import com.farukaygun.yorozuyalist.model.anime.AnimeNode

interface ISeasonalAnimeClickListener {
    fun onSeasonalAnimeClicked(view: View, seasonalAnimeData: AnimeNode)
}

interface ISuggestedAnimeClickListener {
    fun onSuggestedAnimeClicked(view: View, suggestedAnimeData: AnimeNode)
}
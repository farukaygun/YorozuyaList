package com.farukaygun.yorozuyalist.adapter

import android.view.View
import com.farukaygun.yorozuyalist.model.anime.SeasonalNode

interface ISeasonalAnimeClickListener {
    fun onSeasonalAnimeClicked(view: View, seasonalAnimeData: SeasonalNode)
}
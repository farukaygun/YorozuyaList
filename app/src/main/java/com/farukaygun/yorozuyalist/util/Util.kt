package com.farukaygun.yorozuyalist.util

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.farukaygun.yorozuyalist.R
import okhttp3.MediaType
import java.util.*
import kotlin.collections.ArrayList

// DATA BINDING
fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String) {
    view.downloadFromUrl(url, placeholderProgressBar(view.context))
}

@SuppressLint("SetTextI18n")
@BindingAdapter("android:formatInt")
fun formatInt(view: TextView, value: Int) {
    view.text = "%,.0f".format(Locale.getDefault(), value.toBigDecimal())
}

@SuppressLint("SetTextI18n")
@BindingAdapter("android:mediaType", "android:numEpisodes")
fun formatMediaType(view: TextView, mediaType: String, numEpisodes: Int) {
    val animeMediaTypeList: List<String> = listOf("tv", "movie", "ona", "ova")
    val mangaMediaTypeList: List<String> = listOf("manga", "light_novel", "novel")
    var mediaType = mediaType
    if (animeMediaTypeList.contains(mediaType)) {
        when(mediaType) {
            "tv" -> mediaType = view.context.getString(R.string.tv)
            "movie" -> mediaType = view.context.getString(R.string.movie)
            "ona" -> mediaType = view.context.getString(R.string.ona)
            "ova" -> mediaType = view.context.getString(R.string.ova)
        }
        view.text = "$mediaType (${if(numEpisodes > 0) "$numEpisodes ${view.context.getString(R.string.episodes)}" else "?"})"
    }
    else if(mangaMediaTypeList.contains(mediaType)) {
        when(mediaType) {
            "manga" -> mediaType = view.context.getString(R.string.manga)
            "light_novel" -> mediaType = view.context.getString(R.string.light_novel)
            "novel" -> mediaType = view.context.getString(R.string.novel)
        }
        view.text = "$mediaType (${if(numEpisodes > 0) "$numEpisodes ${view.context.getString(R.string.episodes)}" else "?"})"
    }
}

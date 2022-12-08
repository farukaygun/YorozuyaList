package com.farukaygun.yorozuyalist.util

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import java.util.*

// DATA BINDING
fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?) {
    view.downloadFromUrl(url, placeholderProgressBar(view.context))
}

@BindingAdapter("android:formatInt")
fun formatInt(view: TextView, value: Int) {
    view.formatInt(value)
}

@BindingAdapter("android:mediaType", "android:numEpisodes")
fun formatMediaType(view: TextView, mediaType: String, numEpisodes: Int) {
    view.formatMediaType(mediaType, numEpisodes)
}

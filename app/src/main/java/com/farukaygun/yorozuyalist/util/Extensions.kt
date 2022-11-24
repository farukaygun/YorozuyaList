package com.farukaygun.yorozuyalist.util

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farukaygun.yorozuyalist.R

fun ImageView.downloadFromUrl(url: String, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.overflow)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}
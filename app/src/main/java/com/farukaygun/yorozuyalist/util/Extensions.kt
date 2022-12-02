package com.farukaygun.yorozuyalist.util

import android.icu.text.DecimalFormat
import android.icu.text.SimpleDateFormat
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farukaygun.yorozuyalist.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun ImageView.downloadFromUrl(url: String, progressDrawable: CircularProgressDrawable = placeholderProgressBar(this.context)) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.overflow)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun TextView.formatDate(date: String) {
    val subStringDate = date.subSequence(0, 10).toString()

    val formattedDate: String = if (android.os.Build.VERSION.SDK_INT >= 26) {
        val parsedDate = LocalDate.parse(subStringDate)
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        parsedDate.format(formatter)
    } else {
        val parser = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        formatter.format(parser.parse(subStringDate))
    }

    this.text = formattedDate
}
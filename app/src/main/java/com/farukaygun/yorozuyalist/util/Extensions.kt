package com.farukaygun.yorozuyalist.util

import android.icu.text.SimpleDateFormat
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farukaygun.yorozuyalist.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable = placeholderProgressBar(this.context)) {

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

fun TextView.formatMediaType(mediaType: String, numEpisodes: Int) {
    val animeMediaTypeList: List<String> = listOf("tv", "movie", "ona", "ova")
    val mangaMediaTypeList: List<String> = listOf("manga", "light_novel", "novel")
    var mediaType = mediaType
    when(mediaType) {
        "tv" -> mediaType = this.context.getString(R.string.tv)
        "movie" -> mediaType = this.context.getString(R.string.movie)
        "ona" -> mediaType = this.context.getString(R.string.ona)
        "ova" -> mediaType = this.context.getString(R.string.ova)
        "manga" -> mediaType = this.context.getString(R.string.manga)
        "light_novel" -> mediaType = this.context.getString(R.string.light_novel)
        "novel" -> mediaType = this.context.getString(R.string.novel)
    }
    this.text = "$mediaType (${if(numEpisodes > 0) "$numEpisodes ${this.context.getString(R.string.episodes)}" else "?"})"
}

fun TextView.formatStatus(status: String) {
    var status = status
    when(status) {
        "currently_airing" -> status = "Airing"
        "finished_airing" -> status = "Finished"
    }
    this.text = status
}
package com.farukaygun.yorozuyalist.util

import android.annotation.SuppressLint
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
    val animeMediaType = listOf("tv", "movie", "ona", "ova", "music", "special")
    val mangaMediaType = listOf("manga", "light_novel", "novel", "web_novel", "manhwa", "manhua", "one_shot")
    var _mediaType = mediaType
    when(mediaType) {
        animeMediaType[0] -> _mediaType = this.context.getString(R.string.tv)
        animeMediaType[1] -> _mediaType = this.context.getString(R.string.movie)
        animeMediaType[2] -> _mediaType = this.context.getString(R.string.ona)
        animeMediaType[3] -> _mediaType = this.context.getString(R.string.ova)
        animeMediaType[4] -> _mediaType = context.getString(R.string.music)
        animeMediaType[5] -> _mediaType = context.getString(R.string.special)
        mangaMediaType[0] -> _mediaType = this.context.getString(R.string.manga)
        mangaMediaType[1] -> _mediaType = this.context.getString(R.string.light_novel)
        mangaMediaType[2] -> _mediaType = this.context.getString(R.string.novel)
        mangaMediaType[3] -> _mediaType = this.context.getString(R.string.web_novel)
        mangaMediaType[4] -> _mediaType = context.getString(R.string.manhwa)
        mangaMediaType[5] -> _mediaType = context.getString(R.string.manhua)
        mangaMediaType[6] -> _mediaType = context.getString(R.string.one_shot)
    }
    if (animeMediaType.contains(mediaType))
        this.text = if(numEpisodes > 0) this.context.getString(R.string.episodes, _mediaType, numEpisodes) else "$_mediaType (??)"
    else
        this.text = if(numEpisodes > 0) this.context.getString(R.string.chapters, _mediaType, numEpisodes) else "$_mediaType (??)"
}

fun TextView.formatStatus(status: String) {
    var status = status
    when(status) {
        "currently_airing" -> status = context.getString(R.string.airing)
        "finished_airing", "finished" -> status = context.getString(R.string.finished)
        "currently_publishing" -> status = context.getString(R.string.publishing)
        "not_yet_aired" -> status = context.getString(R.string.not_yet_aired)
    }
    this.text = status
}

@SuppressLint("SetTextI18n")
fun TextView.formatInt(value: Int) {
    this.text = "%,.0f".format(Locale.getDefault(), value.toBigDecimal())
}

fun TextView.formatSource(source: String) {
    var source = source
    when(source) {
        "original" -> source = this.context.getString(R.string.original)
        "manga" -> source = this.context.getString(R.string.manga)
        "light_novel" -> source = this.context.getString(R.string.light_novel)
        "novel" -> source = this.context.getString(R.string.novel)
        "web_novel" -> source = this.context.getString(R.string.web_novel)
    }
    this.text = source
}
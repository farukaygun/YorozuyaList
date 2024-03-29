package com.farukaygun.yorozuyalist.util

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farukaygun.yorozuyalist.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.absoluteValue

fun ImageView.downloadFromUrl(
	url: String?,
	progressDrawable: CircularProgressDrawable = placeholderProgressBar(
		this.context
	),
) {

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

	val formattedDate: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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
	val mangaMediaType =
		listOf("manga", "light_novel", "novel", "web_novel", "manhwa", "manhua", "one_shot")
	var _mediaType = mediaType
	when (mediaType) {
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
		this.text = if (numEpisodes > 0) this.context.getString(
			R.string.media_type_episodes,
			_mediaType,
			numEpisodes
		) else "$_mediaType (??)"
	else
		this.text = if (numEpisodes > 0) this.context.getString(
			R.string.chapters,
			_mediaType,
			numEpisodes
		) else "$_mediaType (??)"
}

fun TextView.formatStatus(status: String) {
	var _status = status
	when (status) {
		"currently_airing" -> _status = context.getString(R.string.airing)
		"finished_airing", "finished" -> _status = context.getString(R.string.finished)
		"currently_publishing" -> _status = context.getString(R.string.publishing)
		"not_yet_aired" -> _status = context.getString(R.string.not_yet_aired)
	}
	this.text = _status
}

@SuppressLint("SetTextI18n")
fun TextView.formatInt(value: Int) {
	this.text = "%,.0f".format(Locale.getDefault(), value.toBigDecimal())
}

fun TextView.formatSource(source: String) {
	var _source = source
	when (source) {
		"original" -> _source = this.context.getString(R.string.original)
		"manga" -> _source = this.context.getString(R.string.manga)
		"light_novel" -> _source = this.context.getString(R.string.light_novel)
		"novel" -> _source = this.context.getString(R.string.novel)
		"web_novel" -> _source = this.context.getString(R.string.web_novel)
	}
	this.text = _source
}

@SuppressLint("SetTextI18n")
fun TextView.formatSeason(season: String?, year: Int?) {
	var _season = if (season.isNullOrEmpty()) "??" else season
	val _year = if (year == 0) "" else year

	when (season) {
		"fall" -> _season = context.getString(R.string.fall)
		"winter" -> _season = context.getString(R.string.winter)
		"spring" -> _season = context.getString(R.string.spring)
		"summer" -> _season = context.getString(R.string.summer)
	}
	this.text = "$_season $_year"
}

fun View.hideKeyboard() {
	val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	imm.hideSoftInputFromWindow(windowToken, 0)
}

@SuppressLint("SimpleDateFormat")
fun TextView.startTime(startTime: String?, weekDay: String?) {
	if (startTime == null) {
		this.text = "??"
		return
	}

	val jpTime = Calendar.currentJapanHour
	val currentJpWeekDay = Calendar.currentJapanWeekday
	val time: Int

	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
		time = LocalTime.parse(startTime, DateTimeFormatter.ISO_TIME).hour
	} else {
		val sdf = SimpleDateFormat("HH:mm")
		val date = sdf.parse(startTime)
		val calendar = java.util.Calendar.getInstance()
		calendar.time = date
		val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
		time = hour
	}

	val remaining = time - jpTime

	if (currentJpWeekDay == weekDay && remaining > 0)
		this.text = context.getString(R.string.airing_in, remaining)
	else this.text = context.getString(R.string.aired_ago, remaining.absoluteValue)
}
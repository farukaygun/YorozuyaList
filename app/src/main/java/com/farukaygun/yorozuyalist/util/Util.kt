package com.farukaygun.yorozuyalist.util

import android.content.Context
import android.text.InputFilter
import android.text.Spanned
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

@BindingAdapter("android:formatStatus")
fun formatStatus(view: TextView, status: String) {
	view.formatStatus(status)
}

@BindingAdapter("android:season", "android:year")
fun formatSeason(view: TextView, season: String?, year: Int?) {
	view.formatSeason(season, year)
}

@BindingAdapter("android:startTime")
fun startTime(view: TextView, startTime: String?) {
	view.startTime(startTime)
}

class InputFilterMinMax(
    private var min: Int,
    private var max: Int,
) : InputFilter {

	override fun filter(
		source: CharSequence?,
		start: Int,
		end: Int,
		dest: Spanned?,
		dstart: Int,
		dend: Int,
	): CharSequence? {
		try {
			val input = Integer.parseInt(dest.toString() + source.toString())
			if (isInRange(min, max, input)) return null
			else if (input + 1 == min) return (input + 1).toString()
			else if (input - 1 == max) return (input - 1).toString()
		} catch (nfe: NumberFormatException) {
			nfe.stackTrace
		}
		return ""
	}

	private fun isInRange(min: Int, max: Int, input: Int): Boolean {
		return if (max > min)
			input in min..max
		else
			input in max..min
	}

}
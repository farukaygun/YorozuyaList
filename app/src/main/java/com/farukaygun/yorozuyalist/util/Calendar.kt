package com.farukaygun.yorozuyalist.util

import java.time.LocalDate
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Build
import java.util.Locale

class Calendar {
	companion object {

		private val calendar: Calendar by lazy {
			Calendar.getInstance(Locale.getDefault())
		}

		private val jpCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.ENGLISH)
		private val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
		val currentJapanHour = jpCalendar.get(Calendar.HOUR_OF_DAY)

		val currentWeekday = when(weekDay) {
			2 -> Constants.MONDAY
			3 -> Constants.TUESDAY
			4 -> Constants.WEDNESDAY
			5 -> Constants.THURSDAY
			6 -> Constants.FRIDAY
			7 -> Constants.SATURDAY
			1 -> Constants.SUNDAY
			else -> Constants.MONDAY
		}

		val currentJapanWeekday = when(jpCalendar.get(Calendar.DAY_OF_WEEK)) {
			2 -> Constants.MONDAY
			3 -> Constants.TUESDAY
			4 -> Constants.WEDNESDAY
			5 -> Constants.THURSDAY
			6 -> Constants.FRIDAY
			7 -> Constants.SATURDAY
			1 -> Constants.SUNDAY
			else -> Constants.MONDAY
		}

		fun getYearAndSeason(): Pair<Int, Seasons> {
			val (year, month) = getYearAndMonth()
			return when (month) {
				Months.JANUARY, Months.FEBRUARY, Months.MARCH -> Pair(year, Seasons.WINTER)
				Months.APRIL, Months.MAY, Months.JUNE -> Pair(year, Seasons.SPRING)
				Months.JULY, Months.AUGUST, Months.SEPTEMBER -> Pair(year, Seasons.SUMMER)
				Months.OCTOBER, Months.NOVEMBER, Months.DECEMBER -> Pair(year, Seasons.FALL)
			}
		}

		private fun getYearAndMonth(): Pair<Int, Months> {
			val year: Int
			val month = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				val date = LocalDate.now()
				year = date.year
				date.month.value
			} else {
				year = calendar.get(Calendar.YEAR)
				calendar.get(Calendar.MONTH) + 1
			}
			return Pair(year, Months.values().find { it.value == month }!!)
		}
	}

	enum class Seasons(val value: String) {
		WINTER("winter"),
		SPRING("spring"),
		SUMMER("summer"),
		FALL("fall")
	}

	enum class Months(val value: Int) {
		JANUARY(1),
		FEBRUARY(2),
		MARCH(3),
		APRIL(4),
		MAY(5),
		JUNE(6),
		JULY(7),
		AUGUST(8),
		SEPTEMBER(9),
		OCTOBER(10),
		NOVEMBER(11),
		DECEMBER(12)
	}
}
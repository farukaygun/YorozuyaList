package com.farukaygun.yorozuyalist.util

import java.time.LocalDate
import android.icu.util.Calendar

class Calendar {
    companion object {
        fun getYearAndSeason(): Pair<Int, Seasons> {
            val (year, month) = getYearAndMonth()
            return when(month) {
                Months.JANUARY, Months.FEBRUARY, Months.MARCH -> Pair(year, Seasons.WINTER)
                Months.APRIL, Months.MAY, Months.JUNE -> Pair(year, Seasons.SPRING)
                Months.JULY, Months.AUGUST, Months.SEPTEMBER -> Pair(year, Seasons.SUMMER)
                Months.OCTOBER, Months.NOVEMBER, Months.DECEMBER -> Pair(year, Seasons.FALL)
            }
        }

        private fun getYearAndMonth(): Pair<Int, Months> {
            val year: Int
            val month = if (android.os.Build.VERSION.SDK_INT >= 26) {
                val date = LocalDate.now()
                year = date.year
                date.month.value
            } else {
                val calendar = Calendar.getInstance()
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
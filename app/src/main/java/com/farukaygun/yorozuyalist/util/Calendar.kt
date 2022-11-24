package com.farukaygun.yorozuyalist.util

class Calendar {
    companion object {
        fun getSeason(month: Months): Seasons {
            return when(month) {
                Months.DECEMBER, Months.JANUARY, Months.FEBRUARY -> Seasons.WINTER
                Months.MARCH, Months.APRIL, Months.MAY -> Seasons.SPRING
                Months.JUNE, Months.JULY, Months.AUGUST -> Seasons.SUMMER
                Months.SEPTEMBER, Months.OCTOBER, Months.NOVEMBER -> Seasons.FALL
            }
        }

        fun getMonth(value: Int): Months? = Months.values().find { it.value == value }
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
package com.farukaygun.yorozuyalist.util

class Calendar {
    companion object {
        fun getSeason(month: Months): Seasons {
            return when(month) {
                Months.JANUARY, Months.FEBRUARY, Months.MARCH -> Seasons.WINTER
                Months.APRIL, Months.MAY, Months.JUNE -> Seasons.SPRING
                Months.JULY, Months.AUGUST, Months.SEPTEMBER -> Seasons.SUMMER
                Months.OCTOBER, Months.NOVEMBER, Months.DECEMBER -> Seasons.FALL
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
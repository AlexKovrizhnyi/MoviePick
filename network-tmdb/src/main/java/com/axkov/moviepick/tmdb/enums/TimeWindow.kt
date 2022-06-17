package com.axkov.moviepick.tmdb.enums

enum class TimeWindow(private val time: String) {
    DAY("day"), WEEK("week");

    override fun toString(): String {
        return time
    }
}
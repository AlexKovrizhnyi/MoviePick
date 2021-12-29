package com.axkov.moviepick.api.models

enum class TimeWindow(private val time: String) {
    DAY("day"), WEEK("week");

    override fun toString(): String {
        return time
    }
}
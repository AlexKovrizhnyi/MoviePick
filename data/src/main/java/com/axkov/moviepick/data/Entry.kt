package com.axkov.moviepick.data

import com.axkov.moviepick.data.entities.MoviePickEntity

interface Entry : MoviePickEntity {
    val movieId: Long
}

interface PaginatedEntry: Entry {
    val page: Int
}
package com.axkov.moviepick.database.entities

interface Entry : MoviePickEntity {
    val movieId: Long
}

interface PaginatedEntry: Entry {
    val page: Int
}
package com.axkov.moviepick.data.resultentities

import com.axkov.moviepick.data.Entry
import com.axkov.moviepick.data.entities.MovieEntity
import java.util.*

interface EntryWithMovie<E : Entry> {
    var entry: E

    var relations: List<MovieEntity>

    val movie: MovieEntity
        get() {
            check(relations.size == 1)
            return relations[0]
        }

    fun generateStableId(): Long {
        return Objects.hash(entry::class.java.name, entry.movieId).toLong()
    }
}
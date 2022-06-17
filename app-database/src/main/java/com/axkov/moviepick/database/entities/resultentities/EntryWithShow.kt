package com.axkov.moviepick.database.entities.resultentities

import com.axkov.moviepick.database.entities.Entry
import com.axkov.moviepick.database.entities.MovieEntity
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
package com.axkov.moviepick.data.resultentities

import androidx.room.Embedded
import androidx.room.Relation
import com.axkov.moviepick.data.entities.MovieEntity
import com.axkov.moviepick.data.entities.PopularMovieEntry
import java.util.*

class PopularEntryWithMovie : EntryWithMovie<PopularMovieEntry> {

    @Embedded
    override lateinit var entry: PopularMovieEntry

    @Relation(parentColumn = "movie_id", entityColumn = "id")
    override lateinit var relations: List<MovieEntity>

    override fun equals(other: Any?): Boolean = when {
        other === this -> true
        other is PopularEntryWithMovie -> {
            entry == other.entry && relations == other.relations
        }
        else -> false
    }

    override fun hashCode(): Int = Objects.hash(entry, relations)
}
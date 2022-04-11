package com.axkov.moviepick.data.entities

import androidx.room.*
import com.axkov.moviepick.data.PaginatedEntry

@Entity(
    tableName = "popular_movies",
    indices = [
        Index(value = ["movie_id"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("movie_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PopularMovieEntry(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,

    @ColumnInfo(name = "movie_id")
    override val movieId: Long,

    override val page: Int,

    @ColumnInfo(name = "page_order")
    val pageOrder: Int

) : PaginatedEntry
package com.axkov.moviepick.database.entities

import androidx.room.*

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
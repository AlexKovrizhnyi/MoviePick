package com.axkov.moviepick.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies",
    indices = [Index(value = ["tmdb_id"], unique = true)]
)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    override val id: Long = 0,

    @ColumnInfo(name = "tmdb_id")
    override val tmdbId: Long,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "original_title")
    val originalTitle: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

//    @ColumnInfo(name = "runtime")
//    val runtime: Int = 0,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int = 0,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = 0.0

) : MoviePickEntity, TmdbIdEntity
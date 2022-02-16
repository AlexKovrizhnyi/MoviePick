package com.axkov.moviepick.data.daos

import androidx.room.*
import com.axkov.moviepick.data.entities.MovieEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<MovieEntity>)

    @Transaction
    @Query("SELECT * FROM movies WHERE category=:category")
    abstract fun entriesObservable(category: String): Observable<List<MovieEntity>>

//    fun entriesObservable(category: String, count: Int, offset: Int): Observable<List<MovieEntity>>

    @Query("DELETE FROM movies")
    abstract fun deleteAll()
}
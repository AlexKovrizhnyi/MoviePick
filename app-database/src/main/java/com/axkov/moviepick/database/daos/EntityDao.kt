package com.axkov.moviepick.database.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.axkov.moviepick.database.entities.MoviePickEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

abstract class EntityDao<in E: MoviePickEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: E): Maybe<Long>

    @Insert
    abstract fun insertAll(vararg entity: E): Completable

    @Insert
    abstract fun insertAll(entities: List<E>): Completable

    @Update
    abstract fun update(entity: E): Completable

    @Delete
    abstract fun deleteEntity(entity: E): Completable

}
package com.axkov.moviepick.database.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.axkov.moviepick.database.entities.PaginatedEntry
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

abstract class PaginatedEntryDao<E : PaginatedEntry> : EntryDao<E>() {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun insert(entity: E): Maybe<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun insertAll(entities: List<E>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override fun insertAll(vararg entity: E): Completable

    abstract fun deletePage(page: Int): Completable

    abstract fun getLastPage(): Maybe<Int>
}
package com.axkov.moviepick.database.daos

import com.axkov.moviepick.database.entities.Entry
import io.reactivex.rxjava3.core.Completable

abstract class EntryDao<E: Entry>: EntityDao<E>() {

    abstract fun deleteAll(): Completable
}
package com.axkov.moviepick.data.daos

import com.axkov.moviepick.data.Entry
import io.reactivex.rxjava3.core.Completable

abstract class EntryDao<E: Entry>: EntityDao<E>() {

    abstract fun deleteAll(): Completable
}
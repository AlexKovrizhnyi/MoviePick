package com.axkov.moviepick.database.utils

import com.axkov.moviepick.database.daos.EntityDao
import com.axkov.moviepick.database.entities.MoviePickEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface EntityInserter {

    fun <E: MoviePickEntity> insertOrUpdate(dao: EntityDao<E>, entities: List<E>): Completable

    fun <E: MoviePickEntity> insertOrUpdate(dao: EntityDao<E>, entity: E): Maybe<Long>
}
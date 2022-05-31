package com.axkov.moviepick.data.utils

import android.database.sqlite.SQLiteException
import com.axkov.moviepick.data.daos.EntityDao
import com.axkov.moviepick.data.entities.MoviePickEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber
import javax.inject.Inject

class MoviePickEntityInserter @Inject constructor(
    private val transactionRunner: DatabaseTransactionRunner
) : EntityInserter {

    override fun <E : MoviePickEntity> insertOrUpdate(
        dao: EntityDao<E>,
        entities: List<E>
    ): Completable = transactionRunner {
            Observable.fromIterable(entities)
                .flatMapMaybe { insertOrUpdate(dao, it) }
                .ignoreElements()
        }

    override fun <E : MoviePickEntity> insertOrUpdate(dao: EntityDao<E>, entity: E): Maybe<Long> {
        Timber.v("insertOrUpdate: $entity")

        return if (entity.id == 0L) {
            dao.insert(entity)
                .errorMessage("Error while inserting entity: $entity")

        } else {
            dao.update(entity)
                .andThen(Maybe.just(entity.id))
                .errorMessage("Error while updating entity: $entity")
        }
    }

    // TODO: Maybe this function should be in the core.util package as a universal extension
    private fun <T> Maybe<T>.errorMessage(message: String): Maybe<T> {
        return this.onErrorResumeNext { throwable ->
            Maybe.error {
                if (throwable is SQLiteException) {
                    SQLiteException(message).apply {
                        initCause(throwable)
                    }
                } else {
                    throwable
                }
            }
        }
    }
}
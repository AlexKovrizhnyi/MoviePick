package com.axkov.moviepick.database.utils

import com.axkov.moviepick.database.MoviePickRoomDatabase
import java.util.concurrent.Callable
import javax.inject.Inject

internal class RoomTransactionRunner @Inject constructor(
    private val db: MoviePickRoomDatabase
) : DatabaseTransactionRunner {

    override fun <T> invoke(block: () -> T): T = db.runInTransaction(Callable { block() })

}
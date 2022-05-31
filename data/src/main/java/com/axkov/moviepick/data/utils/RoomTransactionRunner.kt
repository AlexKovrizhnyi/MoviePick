package com.axkov.moviepick.data.utils

import com.axkov.moviepick.data.MoviePickRoomDatabase
import java.util.concurrent.Callable
import javax.inject.Inject

class RoomTransactionRunner @Inject constructor(
    private val db: MoviePickRoomDatabase
) : DatabaseTransactionRunner {

    override fun <T> invoke(block: () -> T): T = db.runInTransaction(Callable { block() })

}
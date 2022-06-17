package com.axkov.moviepick.database.utils

interface DatabaseTransactionRunner {
     operator fun <T> invoke(block: () -> T): T
}
package com.axkov.moviepick.data.utils

interface DatabaseTransactionRunner {
     operator fun <T> invoke(block: () -> T): T
}
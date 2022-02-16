package com.axkov.moviepick.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class UseCase<in P, out T : Any> {
    operator fun invoke(params: P) = doWork(params)

    protected abstract fun doWork(params: P): T
}

abstract class SubjectUseCase<P : Any, T : Any> {
    private val subject = BehaviorSubject.create<P>()

    operator fun invoke(params: P) = subject.onNext(params)

    protected abstract fun createObservable(params: P): Observable<T>

    fun observe(): Observable<T> = subject
        .distinctUntilChanged()
        .switchMap { createObservable(it) }
}
package android1.myapplication1.usecases.job

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import android1.myapplication1.usecases.job.executor.PostExecutionThread
import android1.myapplication1.usecases.job.executor.ThreadExecutor



/**
 * Abstract class for a UseCase that returns an instance of a [Single].
 */
abstract class FlowableUseCase<T, in P> constructor(
        private val threadExecutor: ThreadExecutor,
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    /**
     * Builds a [Single] which will be used when the current [FlowableUseCase] is executed.
     */
    protected abstract fun buildUseCaseObservable(params: P? = null): Flowable<T>

    /**
     * Executes the current use case.
     */
    open fun execute(observer: DisposableSubscriber<T>, params: P? = null) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.scheduler) as Flowable<T>
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

}
package android1.myapplication1.usecases.job.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * MainThread (UI Thread) implementation based on a [Scheduler]
 * which will execute actions on the Android UI thread
 */
class UiThreadExec @Inject internal constructor() : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}
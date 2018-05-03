package android1.myapplication1.rx.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ImmediateSchedulerProvider : BaseSchedulerProvider {

  override fun computation(): Scheduler {
    return Schedulers.computation()
  }

  override fun io(): Scheduler {
    return Schedulers.io()
  }

  override fun ui(): Scheduler {
    return AndroidSchedulers.mainThread()
  }
}

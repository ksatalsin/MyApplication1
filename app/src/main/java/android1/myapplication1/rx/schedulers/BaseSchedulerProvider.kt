package android1.myapplication1.rx.schedulers

import io.reactivex.Scheduler

interface BaseSchedulerProvider {

  fun computation(): Scheduler

  fun io(): Scheduler

  fun ui(): Scheduler
}

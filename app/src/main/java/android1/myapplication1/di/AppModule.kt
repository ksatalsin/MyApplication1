package android1.myapplication1.di

import android.content.Context
import android.support.annotation.UiThread
import android1.myapplication1.App
import android1.myapplication1.AppLifecycleCallbacks
import android1.myapplication1.data.source.MarvelRepository
import android1.myapplication1.rx.RxSchedulersProvider
import android1.myapplication1.rx.schedulers.SchedulerProvider
import android1.myapplication1.usecases.AllComicsUseCase
import android1.myapplication1.usecases.job.executor.JobExecutor
import android1.myapplication1.usecases.job.executor.PostExecutionThread
import android1.myapplication1.usecases.job.executor.ThreadExecutor
import android1.myapplication1.usecases.job.executor.UiThreadExec
import android1.myapplication1.utils.ConnectionHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(DataModule::class), (UseCaseModule::class)])
class AppModule {

  @Provides
  @Singleton
  fun provideAppContext(app: App): Context = app.applicationContext

  @Singleton
  @Provides
  fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = DebugAppLifecycleCallbacks()

  @Singleton
  @Provides
  fun provideScheduler(): SchedulerProvider = SchedulerProvider()

  @Provides
  internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
    return jobExecutor
  }

  @Provides
  internal fun providePostExecutionThread(uiThread: UiThreadExec): PostExecutionThread {
    return uiThread
  }

  @Provides
  @Singleton
  fun provideRxSchedulersProvider(): RxSchedulersProvider {
    return RxSchedulersProvider()
  }

  @Provides
  @Singleton
  fun provideConnectionHelper(context: Context) = ConnectionHelper(context)

}
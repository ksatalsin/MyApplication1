package android1.myapplication1

import android.app.Application
import android1.myapplication1.di.DaggerAppComponent
import android1.myapplication1.di.applyAutoInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication() {

  @Inject
  lateinit var appLifecycleCallbacks: AppLifecycleCallbacks


  override fun applicationInjector() = DaggerAppComponent.builder()
      .application(this)
      .build()

  override fun onCreate() {
    super.onCreate()
    applyAutoInjector()
    appLifecycleCallbacks.onCreate(this)
  }

  override fun onTerminate() {
    appLifecycleCallbacks.onTerminate(this)
    super.onTerminate()
  }
}

interface AppLifecycleCallbacks {

  fun onCreate(application: Application)

  fun onTerminate(application: Application)
}
package android1.myapplication1.di

import android.app.Application
import android1.myapplication1.AppLifecycleCallbacks

class DebugAppLifecycleCallbacks : AppLifecycleCallbacks {

  override fun onCreate(application: Application) {
   // Timber.plant(Timber.DebugTree())
  }

  override fun onTerminate(application: Application) {

  }
}

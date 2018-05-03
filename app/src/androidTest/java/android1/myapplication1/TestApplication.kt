package android1.myapplication1

import android.support.test.InstrumentationRegistry
import android1.myapplication1.di.DaggerTestApplicationComponent
import android1.myapplication1.di.TestApplicationComponent
import android1.myapplication1.di.applyAutoInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class TestApplication : DaggerApplication() {

  val component by lazy {
    DaggerTestApplicationComponent.builder()
        .application(this)
        .build()
  }

 /* @Inject
  lateinit var appLifecycleCallbacks: AppLifecycleCallbacks

*/
  companion object {

    fun appComponent(): TestApplicationComponent {
      return (InstrumentationRegistry.getTargetContext().applicationContext as TestApplication).
          component
    }
  }

  override fun applicationInjector() = component

  override fun onCreate() {
    super.onCreate()
   // applyAutoInjector()
   // appLifecycleCallbacks.onCreate(this)
  }

  override fun onTerminate() {
   // appLifecycleCallbacks.onTerminate(this)
    super.onTerminate()
  }
}

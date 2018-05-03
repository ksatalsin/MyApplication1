package android1.myapplication1.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.crashlytics.android.Crashlytics
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.fabric.sdk.android.Fabric
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {


  @Inject
  lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector() = androidInjector

  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)
    Fabric.with(this, Crashlytics())
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    val selected = when {
      android.R.id.home == item?.itemId -> {
        this.finish()
        return true
      }
      else -> false
    }
    return selected || super.onOptionsItemSelected(item)
  }

}

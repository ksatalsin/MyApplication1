package android1.myapplication1.di

import android.arch.lifecycle.ViewModelProvider
import android1.myapplication1.ui.maincomicslist.MainComicsListActivity
import android1.myapplication1.ui.maincomicslist.di.MainComicsListModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class UiModule {

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @ContributesAndroidInjector(modules = [(MainComicsListModule::class)])
  internal abstract fun contributeMainActivity(): MainComicsListActivity
}
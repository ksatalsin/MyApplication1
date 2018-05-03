package android1.myapplication1.di

import android.app.Application
import android1.myapplication1.TestApplication
import android1.myapplication1.data.source.MarvelRepository
import android1.myapplication1.usecases.job.executor.PostExecutionThread
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [(UiModule::class), (TestApplicationModule::class), (UseCaseModule::class), (AndroidSupportInjectionModule::class)])

interface TestApplicationComponent : AppComponent {


  fun inject(application: TestApplication)

  fun getRepository(): MarvelRepository

  fun postExecutionThread(): PostExecutionThread

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): TestApplicationComponent
  }

}
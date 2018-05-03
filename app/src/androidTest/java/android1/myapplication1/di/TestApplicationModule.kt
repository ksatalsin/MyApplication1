package android1.myapplication1.di

import android.app.Application
import android.content.Context
import android1.myapplication1.AppLifecycleCallbacks
import android1.myapplication1.data.source.MarvelRepository
import android1.myapplication1.data.source.local.MarvelCache
import android1.myapplication1.data.source.local.PreferencesHelper
import android1.myapplication1.data.source.remote.MarvelApi
import android1.myapplication1.data.source.remote.MarvelRemoteDataSource
import android1.myapplication1.usecases.job.executor.JobExecutor
import android1.myapplication1.usecases.job.executor.PostExecutionThread
import android1.myapplication1.usecases.job.executor.ThreadExecutor
import android1.myapplication1.usecases.job.executor.UiThreadExec
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestApplicationModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = DebugAppLifecycleCallbacks()


    @Provides
    internal fun providePreferencesHelper(): PreferencesHelper {
        return mock()
    }

    @Provides
    internal fun provideRepository(): MarvelRepository {
        return mock()
    }

    @Provides
    internal fun provideCache(): MarvelCache {
        return mock()
    }

    @Provides
    internal fun provideRemote(): MarvelRemoteDataSource {
        return mock()
    }

    @Provides
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    internal fun providePostExecutionThread(uiThread: UiThreadExec): PostExecutionThread {
        return uiThread
    }

    @Provides
    internal fun provideService(): MarvelApi {
        return mock()
    }

}
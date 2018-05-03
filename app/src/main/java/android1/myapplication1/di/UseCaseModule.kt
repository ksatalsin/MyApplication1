package android1.myapplication1.di

import android1.myapplication1.data.source.MarvelRepository
import android1.myapplication1.usecases.AllComicsUseCase
import android1.myapplication1.usecases.job.executor.PostExecutionThread
import android1.myapplication1.usecases.job.executor.ThreadExecutor
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

  @Provides
  fun provideAllComicsUseCase(marvelRepository: MarvelRepository,
      threadExecutor: ThreadExecutor,
      postExecutionThread: PostExecutionThread) = AllComicsUseCase(marvelRepository,
      threadExecutor,
      postExecutionThread)
}
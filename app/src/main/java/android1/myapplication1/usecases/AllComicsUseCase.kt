package android1.myapplication1.usecases

import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.data.source.MarvelRepository
import android1.myapplication1.usecases.job.FlowableUseCase
import android1.myapplication1.usecases.job.executor.PostExecutionThread
import android1.myapplication1.usecases.job.executor.ThreadExecutor
import io.reactivex.Flowable
import javax.inject.Inject


class AllComicsUseCase @Inject constructor(private val repository: MarvelRepository,
                                            threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread):
        FlowableUseCase<List<ComicsEntity>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?) = repository.getComics()


}
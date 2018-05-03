package android1.myapplication1

import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.data.source.MarvelRepository
import android1.myapplication1.usecases.AllComicsUseCase
import android1.myapplication1.usecases.job.executor.PostExecutionThread
import android1.myapplication1.usecases.job.executor.ThreadExecutor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class UseCaseTest {

    private lateinit var comicsUseCase: AllComicsUseCase
    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var repository: MarvelRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        repository = mock()
        comicsUseCase = AllComicsUseCase(repository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        comicsUseCase.buildUseCaseObservable(null)
        verify(repository).getComics()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        mockList(Flowable.just(StubUtilsUnit.makeList(2)))
        val testObserver = comicsUseCase.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val items = StubUtilsUnit.makeList(2)

        mockList(Flowable.just(items))

        val testObserver = comicsUseCase.buildUseCaseObservable(null).test()

        testObserver.assertValue(items)
    }

    private fun mockList(single: Flowable<List<ComicsEntity>>) {
        whenever(repository.getComics())
                .thenReturn(single)
    }

}
package android1.myapplication1.ui.maincomicslist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android1.myapplication1.data.LiveResponse
import android1.myapplication1.data.LiveResponseStatus
import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.usecases.AllComicsUseCase
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject


class MainComicsListViewModel @Inject constructor(
    private val allComicsUseCase: AllComicsUseCase) : ViewModel() {

  val empty = ObservableBoolean(false)
  val isLoading = ObservableBoolean(false)
  val comicsLive: MutableLiveData<LiveResponse<List<ComicsEntity>>> =
      MutableLiveData()

  init {
    fetchComics()
  }

  fun onRefresh() {

  }

  override fun onCleared() {
    allComicsUseCase.dispose()
    super.onCleared()
  }

  fun fetchComics() {
    comicsLive.postValue(LiveResponse(LiveResponseStatus.LOADING, null, null))
    allComicsUseCase.execute(AllComicsSubscriber())
  }

  inner class AllComicsSubscriber : DisposableSubscriber<List<ComicsEntity>>() {

    override fun onComplete() {
    }

    override fun onNext(t: List<ComicsEntity>) {
      comicsLive.postValue(LiveResponse(LiveResponseStatus.SUCCESS,
          t, null))
    }

    override fun onError(exception: Throwable) {
      comicsLive.postValue(LiveResponse(LiveResponseStatus.ERROR, null, exception.message))
    }

  }


}
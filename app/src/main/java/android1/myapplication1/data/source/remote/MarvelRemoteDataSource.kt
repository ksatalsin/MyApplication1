package android1.myapplication1.data.source.remote

import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.data.source.MarvelDataSource
import android1.myapplication1.rx.schedulers.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject


class MarvelRemoteDataSource @Inject constructor(
    private val schedulerProvider: SchedulerProvider, var api: MarvelApi) : MarvelDataSource {

  override fun isCached() =  Single.just(false)

  override fun getComics(): Flowable<List<ComicsEntity>> {
   return api.getComicsAllRx().map {
        it.body()?.data?.results.orEmpty()
    }
  }
  override fun clearComics() = Completable.complete()

  override fun saveComics(comis: List<ComicsEntity>)= Completable.complete()
}
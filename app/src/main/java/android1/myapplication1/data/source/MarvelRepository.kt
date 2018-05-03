package android1.myapplication1.data.source

import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.data.source.local.MarvelCache
import android1.myapplication1.data.source.local.MarvelLocalDataSource
import android1.myapplication1.data.source.remote.MarvelRemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject

open class MarvelRepository
@Inject constructor(private val marvelRemoteDataSource: MarvelRemoteDataSource,
    private val marvelLocalDataSource: MarvelLocalDataSource,
    private val marvelCache: MarvelCache
) : MarvelDataSource {

  override fun isCached() = marvelCache.isCached()

  override fun clearComics() =
      marvelLocalDataSource.clearComics()

  override fun saveComics(items: List<ComicsEntity>) = marvelCache.saveComics(items)

  override fun getComics(): Flowable<List<ComicsEntity>> {

    return marvelLocalDataSource.isCached()
        .map {
          if (it && !marvelCache.isExpired()) {
            marvelLocalDataSource
          } else marvelRemoteDataSource
        }
        .flatMapPublisher {
          it.getComics()
        }
        .flatMap {
          saveComics(it)
              .toSingle {
                it
              }
              .toFlowable()
        }
  }
}
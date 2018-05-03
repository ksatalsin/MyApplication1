package android1.myapplication1.data.source.local

import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.data.source.MarvelDataSource
import javax.inject.Inject

class MarvelLocalDataSource @Inject constructor(
    private val marvelCache: MarvelCache) : MarvelDataSource {
  override fun isCached() =
      marvelCache.isCached()

  override fun getComics() = marvelCache.getComics()

  override fun clearComics() = marvelCache.clearAll()

  override fun saveComics(comics: List<ComicsEntity>) = marvelCache.saveComics(comics).doOnComplete {
    marvelCache.setLastCacheTime(System.currentTimeMillis())

  }
}

package android1.myapplication1.data.source.local

import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.data.source.local.db.AppDatabase
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

private const val EXPIRE_TIME = (60 * 10 * 1000).toLong()

class MarvelCache @Inject constructor(val database: AppDatabase,
    private val preferencesHelper: PreferencesHelper) {

  internal fun getDatabase(): AppDatabase {
    return database
  }

   fun clearAll(): Completable {
    return Completable.defer {
      database.cachedComics().clearAll()
      Completable.complete()
    }
  }

  fun saveComics(items: List<ComicsEntity>): Completable {
    return Completable.defer {
      items.forEach {
        database.cachedComics().insertComics(
          it)
      }
      Completable.complete()
    }
  }

  fun getComics(): Flowable<List<ComicsEntity>> {
    return Flowable.defer {
      Flowable.just(database.cachedComics().loadComicsAll())
    }
  }


  fun isCached(): Single<Boolean> {
    return Single.defer {
      Single.just(database.cachedComics().loadComicsAll().isNotEmpty())
    }
  }

  fun setLastCacheTime(lastCache: Long) {
    preferencesHelper.lastCacheTime = lastCache
  }

  fun isExpired(): Boolean {
    val currentTime = System.currentTimeMillis()
    val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
    return currentTime - lastUpdateTime > EXPIRE_TIME
  }

  /**
   * Get in millis, the last time the cache was accessed.
   */
  private fun getLastCacheUpdateTimeMillis(): Long {
    return preferencesHelper.lastCacheTime
  }

}
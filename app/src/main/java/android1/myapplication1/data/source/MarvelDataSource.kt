package android1.myapplication1.data.source

import android1.myapplication1.data.model.ComicsEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


interface MarvelDataSource {

  fun getComics(): Flowable<List<ComicsEntity>>

  fun clearComics(): Completable

  fun saveComics(items: List<ComicsEntity>): Completable

  fun isCached() :Single<Boolean>

}

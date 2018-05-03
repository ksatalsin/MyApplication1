package android1.myapplication1.data.source.remote

import android1.myapplication1.data.model.ComicsResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

  @GET("/v1/public/comics")
  fun getComicsAllRx(
      @Query("limit") limit: Int? = 99): Flowable<Response<ComicsResponse>>
}
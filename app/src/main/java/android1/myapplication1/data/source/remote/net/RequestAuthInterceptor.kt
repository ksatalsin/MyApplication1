package com.gc.gctodo.data.source.remote.net

import android.content.Context
import android1.myapplication1.BuildConfig
import android1.myapplication1.utils.getHash
import okhttp3.Interceptor
import okhttp3.Response
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.io.IOException
import java.util.concurrent.TimeUnit

class RequestAuthInterceptor constructor(private val context: Context) : Interceptor {


  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()

    val requestBuilder = originalRequest.newBuilder()
        .header("Content-Type", "application/json")

    val currtTime = System.currentTimeMillis()

    val url = originalRequest.url().newBuilder()
        .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
        .addQueryParameter("hash",
            getHash(currtTime.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY))
        .addQueryParameter("ts", "$currtTime")
        .build()

    val request = originalRequest
        .newBuilder()
        .url(url)
        .build()


    return chain.proceed(request)
  }

  fun getCurrentTimeZoneOffset(): String {
    val tz = DateTimeZone.getDefault()
    val instant = DateTime.now().millis

    val name = tz.getName(instant)

    val offsetInMilliseconds = tz.getOffset(instant).toLong()
    val hours = TimeUnit.MILLISECONDS.toHours(offsetInMilliseconds)
    val min = TimeUnit.MILLISECONDS.toMinutes(offsetInMilliseconds) - hours * 60
    val offset = java.lang.Long.toString(hours)

    return String.format("UTC%+03d:%02d", hours, min)
  }
}
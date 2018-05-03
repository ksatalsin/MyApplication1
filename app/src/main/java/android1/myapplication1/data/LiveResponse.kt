package android1.myapplication1.data

import android1.myapplication1.data.LiveResponseStatus.ERROR
import android1.myapplication1.data.LiveResponseStatus.LOADING
import android1.myapplication1.data.LiveResponseStatus.SUCCESS


class LiveResponse<out T> constructor(val status: LiveResponseStatus,
    val data: T?,
    val error: String?) {
  companion object {

    fun <T> success(data: T): LiveResponse<T> {
      return LiveResponse(SUCCESS, data, null)
    }

    fun <T> error(error: String): LiveResponse<T> {
      return LiveResponse(ERROR, null, error)
    }

    fun <T> loading(): LiveResponse<T> {
      return LiveResponse(LOADING, null, null)
    }


  }
}

enum class LiveResponseStatus {
  SUCCESS,
  ERROR,
  LOADING
}
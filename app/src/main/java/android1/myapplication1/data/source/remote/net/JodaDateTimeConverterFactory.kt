package android1.myapplication1.data.source.remote.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.joda.time.DateTime
import retrofit2.converter.gson.GsonConverterFactory

object JodaDateTimeConverterFactory {

  private fun gson(): Gson {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.registerTypeAdapter(DateTime::class.java, DateTimeTypeConverter())
    return gsonBuilder.create()
  }

  /**
   * Create an instance using `gson` for conversion. The provided `gson` instance has a
   * registered TypeAdapter to handle deserializing [DateTime]. Encoding to JSON and
   * decoding from JSON (when no charset is specified by a header) will use UTF-8.
   */
  fun create(): GsonConverterFactory {
    return GsonConverterFactory.create(gson())
  }
}

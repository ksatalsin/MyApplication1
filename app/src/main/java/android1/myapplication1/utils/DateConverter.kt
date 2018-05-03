package android1.myapplication1.utils

import android.arch.persistence.room.TypeConverter
import org.joda.time.DateTime

class DateConverter {
  @TypeConverter
  fun toDate(timestamp: Long?): DateTime? {

    return when (timestamp) {
      null -> null
      else -> DateTime(timestamp)
    }
  }

  @TypeConverter
  fun toTimestamp(date: DateTime?): Long? {
    return date?.millis
  }
}

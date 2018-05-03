package android1.myapplication1.data.source.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.data.source.local.db.dao.MainComicsListDao
import android1.myapplication1.utils.DateConverter


@Database(entities = [(ComicsEntity::class)], version = 2)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

  abstract fun cachedComics(): MainComicsListDao

  companion object {
    const val DB_NAME = "db-marvel"
  }

  private var INSTANCE: AppDatabase? = null

  private val sLock = Any()

  fun getInstance(context: Context): AppDatabase {
    if (INSTANCE == null) {
      synchronized(sLock) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.applicationContext,
              AppDatabase::class.java, DB_NAME)
              .build()
        }
        return INSTANCE!!
      }
    }
    return INSTANCE!!
  }
}
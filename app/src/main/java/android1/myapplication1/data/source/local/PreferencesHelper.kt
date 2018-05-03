package android1.myapplication1.data.source.local

import android.content.Context
import android.content.SharedPreferences
import android1.myapplication1.BuildConfig

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private const val LAST_CACHE_TIME_KEY = "last_cache_time"
    }

    private val pref: SharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    var lastCacheTime: Long
        get() = pref.getLong(LAST_CACHE_TIME_KEY, 0)
        set(lastCache) = pref.edit().putLong(LAST_CACHE_TIME_KEY, lastCache).apply()

}

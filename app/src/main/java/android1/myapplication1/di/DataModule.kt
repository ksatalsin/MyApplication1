package android1.myapplication1.di

import android.arch.persistence.room.Room
import android.content.Context
import android1.myapplication1.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import android1.myapplication1.data.source.MarvelRepository
import android1.myapplication1.data.source.local.MarvelCache
import android1.myapplication1.data.source.local.MarvelLocalDataSource
import android1.myapplication1.data.source.local.PreferencesHelper
import android1.myapplication1.data.source.local.db.AppDatabase
import android1.myapplication1.data.source.remote.MarvelApi
import android1.myapplication1.data.source.remote.MarvelRemoteDataSource
import com.gc.gctodo.data.source.remote.net.RequestAuthInterceptor
import android1.myapplication1.rx.schedulers.SchedulerProvider
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

	@Singleton
	@Provides
	fun providesOkHttp(context: Context): OkHttpClient = OkHttpClient.Builder()
			.addNetworkInterceptor(RequestAuthInterceptor(context))
			.addNetworkInterceptor(StethoInterceptor())
			.addInterceptor(HttpLoggingInterceptor()
					.apply { level = HttpLoggingInterceptor.Level.BODY })
			.build()

	@Singleton
	@Provides
	fun provideRetrofit(oktHttpClient: OkHttpClient): Retrofit {
		val gson = GsonBuilder()
				.setPrettyPrinting()
				.setVersion(1.0)
				.registerTypeAdapter(DateTime::class.java,
						JsonSerializer<DateTime> { json, _, _ -> JsonPrimitive(ISODateTimeFormat.dateTime().print(json)) })
				.registerTypeAdapter(DateTime::class.java,
						JsonDeserializer<DateTime> { json, _, _ -> ISODateTimeFormat.dateTimeParser().parseDateTime(json.asString) })
				.create()

		return Retrofit.Builder()
				.client(oktHttpClient)
				.baseUrl(BuildConfig.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build()
	}

	@Provides
	@Singleton
	fun provideDatabase(context: Context): AppDatabase = Room.databaseBuilder<AppDatabase>(
			context.applicationContext, AppDatabase::class.java,
			AppDatabase.DB_NAME).fallbackToDestructiveMigration().build()

	@Provides
	@Singleton
	fun providePreferencesHelper(context: Context) = PreferencesHelper(context)

	@Provides
	@Singleton
	fun provideMarvelCache(database: AppDatabase,
			preferencesHelper: PreferencesHelper) = MarvelCache(database,preferencesHelper)

	@Singleton
	@Provides
	fun provideTasksLocalDataSource(baseSchedulerProvider: SchedulerProvider,
	                                appDatabase: AppDatabase, marvelCache: MarvelCache): MarvelLocalDataSource = MarvelLocalDataSource(marvelCache)

	@Singleton
	@Provides
	fun provideMarvelRemoteDataSource(baseSchedulerProvider: SchedulerProvider,
	                                 retrofit: Retrofit): MarvelRemoteDataSource = MarvelRemoteDataSource(baseSchedulerProvider,
			retrofit.create(
					MarvelApi::class.java))

	@Singleton
	@Provides
	fun provideMarvelRepository(marvelRemoteDataSource: MarvelRemoteDataSource,
			marvelLocalDataSource: MarvelLocalDataSource,
			marvelCache: MarvelCache): MarvelRepository = MarvelRepository(
			marvelRemoteDataSource,
			marvelLocalDataSource,
			marvelCache)

	@Singleton
	@Provides
	fun provideMarvelApi(retrofit: Retrofit): MarvelApi
			= retrofit.create(MarvelApi::class.java)
}
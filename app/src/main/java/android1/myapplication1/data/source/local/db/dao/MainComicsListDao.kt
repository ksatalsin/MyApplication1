package android1.myapplication1.data.source.local.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android1.myapplication1.data.model.ComicsEntity

@Dao
interface MainComicsListDao {

	@Query("SELECT * from ComicsEntity")
	fun loadComicsAll(): List<ComicsEntity>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertComics(comicsEntity: ComicsEntity)

	@Query("DELETE FROM ComicsEntity")
	abstract fun clearAll()

}


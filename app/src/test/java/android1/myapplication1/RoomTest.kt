package android1.myapplication1

import android.arch.persistence.room.Room
import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.data.source.local.db.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
open class RoomTest {

    private lateinit var db: AppDatabase

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
                RuntimeEnvironment.application.baseContext,
            AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertSavesData() {
        val items = StubUtilsUnit.makeModel<ComicsEntity>(ComicsEntity::class)
        db.cachedComics().insertComics(items)

        val comics = db.cachedComics().loadComicsAll()
        assert(comics.isNotEmpty())
    }

    @Test
    fun `checkRetrievesData`() {
        val items = StubUtilsUnit.makeList(5)

        items.forEach {
            db.cachedComics().insertComics(it) }

        val retrievedData = db.cachedComics().loadComicsAll()
        assert(retrievedData == items.sortedWith(compareBy({ it.id }, { it.id })))
    }

}
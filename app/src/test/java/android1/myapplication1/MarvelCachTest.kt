package android1.myapplication1

import android.arch.persistence.room.Room
import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.data.source.local.MarvelCache
import android1.myapplication1.data.source.local.PreferencesHelper
import android1.myapplication1.data.source.local.db.AppDatabase
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class MarvelCachTest {

  private var db = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application,
      AppDatabase::class.java).allowMainThreadQueries().build()

  private var preferencesHelper = PreferencesHelper(RuntimeEnvironment.application)


  private val databaseHelper = MarvelCache(db,
      preferencesHelper)

  @Test
  fun clearTablesCompletes() {
    val testObserver = databaseHelper.clearAll().test()
    testObserver.assertComplete()
  }

  //<editor-fold desc="Save">
  @Test
  fun `checkCompletes`() {
    val items = StubUtilsUnit.makeList(2)

    val testObserver = databaseHelper.saveComics(items).test()
    testObserver.assertComplete()
  }

  @Test
  fun checkNumRowsInTable() {
    val count = 2
    val items = StubUtilsUnit.makeList(count)

    databaseHelper.saveComics(items).test()
    checkNumRowsInDbTable(count)
  }
  //</editor-fold>

  //<editor-fold desc="test Observer Completes">
  @Test
  fun testObserverCompletes() {
    val testObserver = databaseHelper.getComics().test()
    testObserver.assertComplete()
  }

  @Test
  fun `checkReturnsData`() {
    val items = StubUtilsUnit.makeList(2)
    insertDb(items)

    val testObserver = databaseHelper.getComics().test()
    //  testObserver.assertValue(bufferooEntities)
  }
  //</editor-fold>

  private fun insertDb(items: List<ComicsEntity>) {
    items.forEach {
      db.cachedComics().insertComics(it)
    }
  }

  private fun checkNumRowsInDbTable(expectedRows: Int) {
    val numberOfRows = db.cachedComics().loadComicsAll().size
    assertEquals(expectedRows, numberOfRows)
  }

}
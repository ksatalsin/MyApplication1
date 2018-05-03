package android1.myapplication1

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.ui.maincomicslist.MainComicsListActivity
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ActivityIntComicsListTest {

    @Rule
    val activity = ActivityTestRule<MainComicsListActivity>(MainComicsListActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        mockRepository(Flowable.just(StubUtils.makeList(2)))
        activity.launchActivity(null)
    }

    @Test
    fun itemsDisplay() {
        val items = StubUtils.makeList(1)
        mockRepository(Flowable.just(items))
        activity.launchActivity(null)
        checkTitleDisplay(items[0], 0)
    }

    @Test
    fun itemsAreScrollable() {
        val items = StubUtils.makeList(20)
        mockRepository(Flowable.just(items))
        activity.launchActivity(null)

        items.forEachIndexed { index, item ->
            onView(withId(R.id.recycler)).perform(RecyclerViewActions.
                scrollToPosition<RecyclerView.ViewHolder>(index))
            checkTitleDisplay(item, index) }
    }

    private fun checkTitleDisplay(comics: ComicsEntity, position: Int) {
        onView(RecyclerViewMatcher.withRecyclerView(R.id.recycler).atPosition(position))
                .check(matches(hasDescendant(withText(comics.title))))

    }

    private fun mockRepository(single: Flowable<List<ComicsEntity>>) {
        whenever(TestApplication.appComponent().getRepository().getComics())
                .thenReturn(single)
    }

}
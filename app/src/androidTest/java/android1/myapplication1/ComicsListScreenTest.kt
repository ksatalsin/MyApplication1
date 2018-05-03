package android1.myapplication1

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android1.myapplication1.ui.maincomicslist.MainComicsListActivity
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class ComicsListScreenTest {

  @Rule
  @JvmField
  var tasksActivityTestRule =
      ActivityTestRule<MainComicsListActivity>(MainComicsListActivity::class.java)

  @Before
  fun resetState() {

  }

  @Test
  fun orientationChange_nodata_is_hiden() {
    tasksActivityTestRule.activity.rotateOrientation()
    onView(withText(R.string.no_data)).check(matches(not(isDisplayed())))
  }


}
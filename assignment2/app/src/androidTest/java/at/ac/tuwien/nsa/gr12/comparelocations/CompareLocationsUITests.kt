package at.ac.tuwien.nsa.gr12.comparelocations

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.ReportPersistenceInterface
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaListInteractions.scrollListToPosition
import com.schibsted.spain.barista.interaction.BaristaSleepInteractions.sleep
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CompareLocationsUITests : KodeinAware {

    override val kodein by closestKodein(InstrumentationRegistry.getInstrumentation().targetContext)

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun when_clickingButtonOnce_then_thereShouldBeOneReport() {
        clickOn(R.id.button)

        assertListItemCount(R.id.reportList, 1)
    }

    @Test
    fun when_clickingButtonMultipleTimes_then_thereShouldBeMultipleReports() {
        repeat(12) {
            clickOn(R.id.button)
        }

        scrollListToPosition(R.id.reportList, 11)

        sleep(2, SECONDS)
        assertDisplayed(withText(containsString("#11")))
    }

    @Test
    fun when_clickingOnReport_then_detailsAreVisible() {
        clickOn(R.id.button)

        onView(withId(R.id.reportList))
            .perform(swipeUp())
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        assertDisplayed(withId(R.id.date))
        assertDisplayed(withId(R.id.gps))
        assertDisplayed(withId(R.id.mls))
        assertDisplayed(withId(R.id.mlsGpsDistance))
        assertDisplayed(withId(R.id.accessPointsList))
        assertDisplayed(withId(R.id.cellTowersList))
        assertDisplayed(withId(R.id.buttonDelete))
        assertDisplayed(withId(R.id.buttonMail))
    }

    @Test
    fun when_clickingDeleteButton_then_reportShouldBeRemoved() {
        clickOn(R.id.button)
        clickOn(R.id.button)
        onView(withId(R.id.reportList))
            .perform(swipeUp())
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))


        clickOn(R.id.buttonDelete)


        assertListItemCount(R.id.reportList, 1)
    }

    @After
    fun tearDown() {
        val reportPersistence: ReportPersistenceInterface by kodein.instance()
        val reports = reportPersistence.getAll()
        reports.forEach { reportPersistence.remove(it) }
    }
}


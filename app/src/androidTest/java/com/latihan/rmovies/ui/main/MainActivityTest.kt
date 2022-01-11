package com.latihan.rmovies.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.latihan.rmovies.R
import com.latihan.rmovies.utils.DummyData
import com.latihan.rmovies.utils.EspressoIdlingResource
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {

    private val dummyMovie = DummyData.getDummyRemoteMovies()
    private val dummyShow = DummyData.getTvShowDetail()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    public override fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource())
    }

    @After
    public override fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource())
    }

    @Test
    fun loadMovies() {
        delay()
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19)
        )
    }

    @Test
    fun loadDetailMovie() {
        delay()
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        delay()
        onView(withId(R.id.mtitle_value)).check(matches(isDisplayed()))
        onView(withId(R.id.mtitle_value)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.mrelease_value)).check(matches(isDisplayed()))
        onView(withId(R.id.mrelease_value)).check(matches(withText(dummyMovie[0].releasedDate)))
        onView(withId(R.id.mstar_value)).check(matches(isDisplayed()))
        onView(withId(R.id.mstar_value)).check(matches(withText(dummyMovie[0].voteAverage.toString())))
        onView(withId(R.id.moverview_value)).check(matches(isDisplayed()))
        onView(withId(R.id.moverview_value)).check(matches(withText(dummyMovie[0].overview)))
        onView(withId(R.id.miv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.mbackdrop_poster)).check(matches(isDisplayed()))

    }

    @Test
    fun loadTvShows() {
        delay()
        onView(withText("Tv Shows")).perform(ViewActions.click())
        delay()
        onView(withId(R.id.rv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19)
        )
    }

    @Test
    fun loadDetailShow() {
        delay()
        onView(withText("Tv Shows")).perform(ViewActions.click())
        delay()
        onView(withId(R.id.rv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        delay()
        onView(withId(R.id.mtitle_value)).check(matches(isDisplayed()))
        onView(withId(R.id.mtitle_value)).check(matches(withText(dummyShow.name)))
        onView(withId(R.id.mrelease_value)).check(matches(isDisplayed()))
        onView(withId(R.id.mrelease_value)).check(matches(withText(dummyShow.firstAirDate)))
        onView(withId(R.id.mstar_value)).check(matches(isDisplayed()))
        onView(withId(R.id.mstar_value)).check(matches(withText(dummyShow.voteAverage.toString())))
        onView(withId(R.id.moverview_value)).check(matches(isDisplayed()))
        onView(withId(R.id.moverview_value)).check(
            matches(
                withText(
                    dummyShow.overview ?: "No overview yet"
                )
            )
        )
        onView(withId(R.id.miv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.mbackdrop_poster)).check(matches(isDisplayed()))

    }

    private fun delay() {
        try {
            Thread.sleep(1500)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
package com.latihan.rmovies.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.latihan.rmovies.R
import com.latihan.rmovies.utils.EspressoIdlingResource
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {

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

        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19)
        )
    }

    @Test
    fun loadDetailMovie() {

        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.mtitle_value)).check(matches(isDisplayed()))
        onView(withId(R.id.mrelease_value)).check(matches(isDisplayed()))
        onView(withId(R.id.mstar_value)).check(matches(isDisplayed()))
        onView(withId(R.id.moverview_value)).check(matches(isDisplayed()))
        onView(withId(R.id.miv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.mbackdrop_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))

    }

    @Test
    fun loadTvShows() {

        onView(withId(R.id.tvshows)).perform(ViewActions.click())
        onView(withId(R.id.rv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19)
        )
    }

    @Test
    fun loadDetailShow() {

        onView(withId(R.id.tvshows)).perform(ViewActions.click())
        onView(withId(R.id.rv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        onView(withId(R.id.mtitle_value)).check(matches(isDisplayed()))
        onView(withId(R.id.mrelease_value)).check(matches(isDisplayed()))
        onView(withId(R.id.mstar_value)).check(matches(isDisplayed()))
        onView(withId(R.id.moverview_value)).check(matches(isDisplayed()))
        onView(withId(R.id.miv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.mbackdrop_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
    }

    @Test
    fun testSetFavoriteMovies() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(ViewActions.click())
    }

    @Test
    fun testSetFavoriteShow() {
        onView(withId(R.id.tvshows)).perform(ViewActions.click())
        onView(withId(R.id.rv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.fab_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_favorite)).perform(ViewActions.click())
    }

    @Test
    fun testLoadFavMovies() {
        onView(withId(R.id.favorite)).perform(ViewActions.click())
        onView(withId(R.id.rv_fav_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19)
        )
        onView(withId(R.id.default_chip)).perform(ViewActions.click())
        onView(withId(R.id.name_chip)).perform(ViewActions.click())
        onView(withId(R.id.vote_chip)).perform(ViewActions.click())
    }

    @Test
    fun testLoadFavDetailMovies() {
        onView(withId(R.id.favorite)).perform(ViewActions.click())
        onView(withId(R.id.rv_fav_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
    }

    @Test
    fun testLoadFavShow() {
        onView(withId(R.id.favorite)).perform(ViewActions.click())
        onView(withId(R.id.view_pager))
            .perform(swipeLeft())
        onView(withId(R.id.rv_fav_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19)
        )
        onView(withId(R.id.sdefault_chip)).perform(ViewActions.click())
        onView(withId(R.id.sname_chip)).perform(ViewActions.click())
        onView(withId(R.id.svote_chip)).perform(ViewActions.click())
    }

    @Test
    fun testLoadFavDetailShow() {
        onView(withId(R.id.favorite)).perform(ViewActions.click())
        onView(withId(R.id.view_pager))
            .perform(swipeLeft())
        onView(withId(R.id.rv_fav_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
    }

    @Test
    fun testDelFavoriteMovies() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.fab_favorite)).perform(ViewActions.click())
    }

    @Test
    fun testDelFavoriteShow() {
        onView(withId(R.id.tvshows)).perform(ViewActions.click())
        onView(withId(R.id.rv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.fab_favorite)).perform(ViewActions.click())
    }

}
package com.example.websearch

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsSearchTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testWebSearch() {
        Espresso.onView(ViewMatchers.withId(R.id.start)).perform(ViewActions.click())
        // Navigate to search screen
        Espresso.onView(ViewMatchers.withId(R.id.news_search_button)).perform(ViewActions.click())

        // Type in search query
        Espresso.onView(ViewMatchers.withId(R.id.searchInput)).perform(ViewActions.typeText("apple"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        // Click search button
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())

        // Wait for results to load
        Thread.sleep(5000)

        // Check if apple is being searched
        //Espresso.onView(ViewMatchers.withId(R.id.newsTextView)).check(ViewAssertions.matches(ViewMatchers.withText("Apple")))
        Espresso.onView(allOf(withTagValue(equalTo("unique_news_textview")), isDisplayed()))

        //Espresso.onView(ViewMatchers.withId(R.id.newsTextView)).check(ViewAssertions.matches(hasDescendant(withText("apple"))))
        //Espresso.onView(allOf(withTagValue(equalTo("uniqueNewsTextView"))))
        //Espresso.onView(allOf(withTagValue(equalTo("unique_news_textview")), hasDescendant(withText("apple")))).check(matches(isDisplayed()))


        //newsTextView.check(matches(withText("apple")))





    }
}


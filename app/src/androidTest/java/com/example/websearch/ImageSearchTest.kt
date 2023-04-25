package com.example.websearch

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageSearchTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testWebSearch() {
        Espresso.onView(ViewMatchers.withId(R.id.start)).perform(ViewActions.click())
        // Navigate to search screen
        Espresso.onView(ViewMatchers.withId(R.id.image_search_button)).perform(ViewActions.click())

        // Type in search query
        Espresso.onView(ViewMatchers.withId(R.id.searchInput)).perform(ViewActions.typeText("apple"))
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())

        // Click search button
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())

        // Wait for results to load
        Thread.sleep(5000)

        // Check if apple.com is in search results
        Espresso.onView(ViewMatchers.withId(R.id.imageTextView))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Apple"))))
    }
}


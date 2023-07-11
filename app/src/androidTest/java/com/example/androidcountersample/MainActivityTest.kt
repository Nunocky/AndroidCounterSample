package com.example.androidcountersample

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Test
    fun test1() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val scenario = launchActivity<MainActivity>()

        onView(withText(appContext.resources.getString(R.string.message))).check( matches(isDisplayed()))
        onView(withId(R.id.button)).check(matches(isDisplayed()))
        onView(withText("0")).check(matches(isDisplayed()))

        // クリック後 "1" が表示される
        onView(withId(R.id.button)).perform(click())
        onView(withText("1")).check(matches(isDisplayed()))
    }
}
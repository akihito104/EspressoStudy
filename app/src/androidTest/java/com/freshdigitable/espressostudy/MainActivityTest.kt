package com.freshdigitable.espressostudy

import android.content.Context
import android.graphics.Rect
import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class MainActivityTest(
    rotation: Float
) {
    @get:Rule
    val scenario = activityScenarioRule<MainActivity>(
        MainActivity.getIntent(
            ApplicationProvider.getApplicationContext<Context>(),
            rotation
        )
    )

    @Test
    fun test_espressoClick() {
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_customClick() {
        onView(withId(R.id.button)).perform(
            GeneralClickAction(
                Tap.SINGLE,
                CustomCoordinatorProvider(),
                Press.FINGER,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_PRIMARY
            )
        )
        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "rotation: {0}")
        fun data(): Iterable<Float> = (0 until 360 step 10).map { it.toFloat() }
    }
}


class CustomCoordinatorProvider : CoordinatesProvider {
    override fun calculateCoordinates(view: View?): FloatArray = Rect().run {
        check(view != null)
        view.getGlobalVisibleRect(this)
        floatArrayOf(exactCenterX(), exactCenterY())
    }
}
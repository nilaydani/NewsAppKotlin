package com.example.nilay

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.nilay.presentation.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.nilay", appContext.packageName)
    }
    @Rule
    @JvmField
    var composeRule : ComposeContentTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun test_discover_news_screen_isVisible() {

        with(composeRule) {
            onNodeWithContentDescription( "Discover", substring = true)
                .assertIsDisplayed()
        }
    }

    @Test
    fun test_search_news_screen_isVisible() {
        composeRule.onNodeWithContentDescription("Search").performClick()
        composeRule.onAllNodesWithContentDescription("search", ignoreCase = true).assertCountEquals(3)
    }

}
package br.com.sailboat.homeinventory.ui

import android.content.Intent
import android.support.test.InstrumentationRegistry.getTargetContext
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.sailboat.homeinventory.ui.product.list.ProductListActivity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LauncherActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(LauncherActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @Test
    fun useAppContext() {
        assertEquals("br.com.sailboat.homeinventory", getTargetContext().packageName)
    }

    @Test
    fun shouldStartProductListAfterLaunch() {
        activityRule.launchActivity(Intent())
        intended(hasComponent(ProductListActivity::class.qualifiedName))
    }

    @Test
    fun shouldFinishAfterLaunch() {
        assertTrue(activityRule.activity.isFinishing)
    }

    @After
    fun tearDown() {
        Intents.release()
    }

}
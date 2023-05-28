package com.example.submissionjetpack

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.submissionjetpack.model.FakeIndomieDataSource
import com.example.submissionjetpack.ui.navigation.Screen
import com.example.submissionjetpack.ui.theme.JetIndomieTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JetIndomieAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController
    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetIndomieTheme() {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                JetIndomieApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("IndomieList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeIndomieDataSource.dummyIndomies[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailIndomie.route)
        composeTestRule.onNodeWithText(FakeIndomieDataSource.dummyIndomies[10].title).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_cart).performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.about_page).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("IndomieList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeIndomieDataSource.dummyIndomies[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailIndomie.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_checkout_rightBackStack() {
        composeTestRule.onNodeWithText(FakeIndomieDataSource.dummyIndomies[4].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailIndomie.route)
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }
}
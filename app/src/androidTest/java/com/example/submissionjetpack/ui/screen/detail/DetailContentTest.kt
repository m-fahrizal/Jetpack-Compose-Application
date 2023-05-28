package com.example.submissionjetpack.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.submissionjetpack.model.Indomie
import com.example.submissionjetpack.model.OrderIndomie
import com.example.submissionjetpack.R
import com.example.submissionjetpack.onNodeWithStringId
import com.example.submissionjetpack.ui.theme.JetIndomieTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailContentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val fakeOrderIndomie = OrderIndomie(
        indomie = Indomie(4, R.drawable.goreng, "Jaket Hoodie Dicoding", 4000),
        count = 0
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            JetIndomieTheme() {
                DetailContent(
                    fakeOrderIndomie.indomie.image,
                    fakeOrderIndomie.indomie.title,
                    fakeOrderIndomie.indomie.price,
                    fakeOrderIndomie.count,
                    onBackClick = {},
                    onAddToCart = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeOrderIndomie.indomie.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.price,
                fakeOrderIndomie.indomie.price
            )
        ).assertIsDisplayed()
    }

    @Test
    fun increaseProduct_buttonEnabled() {
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsNotEnabled()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").assertIsEnabled()
    }

    @Test
    fun increaseProduct_correctCounter() {
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick().performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))
    }

    @Test
    fun decreaseProduct_stillZero() {
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("0"))
    }
}
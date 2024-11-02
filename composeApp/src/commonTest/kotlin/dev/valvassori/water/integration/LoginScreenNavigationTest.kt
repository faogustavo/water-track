package dev.valvassori.water.integration

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.runComposeUiTest
import dev.valvassori.water.ext.renderApp
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class LoginScreenNavigationTest {
    @Test
    fun openForgotPassword() =
        runComposeUiTest {
            renderApp()

            onNodeWithTag("Authentication.ForgotPassword")
                .performScrollTo()
                .performClick()

            onNodeWithTag("Screen.ForgotPassword").assertIsDisplayed()
        }

    @Test
    fun openCreateProfile() =
        runComposeUiTest {
            renderApp()

            onNodeWithTag("Authentication.SignUp")
                .performScrollTo()
                .performClick()

            onNodeWithTag("Screen.CreateProfile").assertIsDisplayed()
        }
}

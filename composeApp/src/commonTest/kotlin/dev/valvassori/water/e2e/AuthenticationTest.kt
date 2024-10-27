package dev.valvassori.water.e2e

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.waitUntilAtLeastOneExists
import dev.valvassori.water.e2e.ext.renderApp
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class AuthenticationTest {
    @Test
    fun onAuthenticateSuccessfully_shouldNavigateToAuthenticatedScreen() =
        runComposeUiTest {
            renderApp()

            waitUntilAtLeastOneExists(hasTestTag("Screen.Login"))

            onNodeWithTag("Authentication.Username").performTextInput("john.doe")
            onNodeWithTag("Authentication.Password").performTextInput("password")
            onNodeWithTag("Authentication.Submit").performScrollTo().performClick()

            waitUntilAtLeastOneExists(hasText("Home"))
        }
}

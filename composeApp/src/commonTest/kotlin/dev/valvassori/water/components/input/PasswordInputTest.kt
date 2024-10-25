package dev.valvassori.water.components.input

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class PasswordInputTest {
    @Test
    fun renderCorrectly() =
        runComposeUiTest {
            renderInput()

            val textToType = "password"
            val hiddenText = "\u2022".repeat(textToType.length)

            onNodeWithTag("PasswordInput").performTextInput(textToType)

            // On input, show only the visual transformation
            onNodeWithTag("PasswordInput").assertTextContains(hiddenText)

            // After click on toggle, show the actual password
            onNodeWithTag("PasswordVisibilityToggle").performClick()
            onNodeWithTag("PasswordInput").assertTextContains(textToType)

            // After click on toggle again, show the visual transformation back
            onNodeWithTag("PasswordVisibilityToggle").performClick()
            onNodeWithTag("PasswordInput").assertTextContains(hiddenText)
        }

    private fun ComposeUiTest.renderInput() {
        setContent {
            var state by remember { mutableStateOf("") }

            PasswordInput(
                value = state,
                onValueChange = { state = it },
                modifier = Modifier.testTag("PasswordInput"),
            )
        }
    }
}

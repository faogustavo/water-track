package dev.valvassori.water.integration

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode
import dev.valvassori.water.analytics.AnalyticsLogger
import dev.valvassori.water.data.Profile
import dev.valvassori.water.error.LoginError
import dev.valvassori.water.helpers.getString
import dev.valvassori.water.repository.SessionRepository
import dev.valvassori.water.screen.LoginScreen
import dev.valvassori.water.viewmodel.LoginViewModel
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.password_validation_short
import watertrack.composeapp.generated.resources.username_validation_short
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class LoginScreenAnalyticsTest {
    private val analyticsLogger: AnalyticsLogger = mock()
    private val sessionRepository: SessionRepository = mock()

    @Test
    fun onLoad_sendPageView() =
        runComposeUiTest {
            setUp()

            renderLoginScreen()

            verify(mode = VerifyMode.exactly(1)) {
                analyticsLogger.logPageView()
            }
        }

    @Test
    fun onUsernameValidationError_logError() =
        runComposeUiTest {
            setUp()

            renderLoginScreen()

            onNodeWithTag("Authentication.Username")
                .performTextInput("a")

            waitForIdle()

            verify(mode = VerifyMode.exactly(1)) {
                analyticsLogger.logError(
                    getString(Res.string.username_validation_short),
                )
            }
        }

    @Test
    fun onPasswordValidationError_logError() =
        runComposeUiTest {
            setUp()

            renderLoginScreen()

            onNodeWithTag("Authentication.Password")
                .performTextInput("less")

            waitForIdle()

            verify(mode = VerifyMode.exactly(1)) {
                analyticsLogger.logError(
                    getString(Res.string.password_validation_short),
                )
            }
        }

    @Test
    fun onClickSubmitWithoutFillingUsernameOrPassword_logErrorForBoth() =
        runComposeUiTest {
            setUp()

            renderLoginScreen()

            onNodeWithTag("Authentication.Submit")
                .performScrollTo()
                .performClick()

            waitForIdle()

            verify(mode = VerifyMode.exactly(2)) {
                analyticsLogger.logError(any())
            }
        }

    @Test
    fun onLoginError_logError() =
        runComposeUiTest {
            setUp()

            renderLoginScreen()

            onNodeWithTag("Authentication.Username")
                .performTextInput("john.doe")

            onNodeWithTag("Authentication.Password")
                .performTextInput("password")

            onNodeWithTag("Authentication.Submit")
                .performScrollTo()
                .performClick()

            waitForIdle()

            verify(mode = VerifyMode.exactly(1)) {
                analyticsLogger.logError(any())
            }
        }

    @Test
    fun onLoginSuccess_logNoError() =
        runComposeUiTest {
            setUp(authenticationResult = Profile("foo", "foo@bar.com").right())

            renderLoginScreen()

            onNodeWithTag("Authentication.Username")
                .performTextInput("john.doe")

            onNodeWithTag("Authentication.Password")
                .performTextInput("password")

            onNodeWithTag("Authentication.Submit")
                .performScrollTo()
                .performClick()

            waitForIdle()

            verify(mode = VerifyMode.exactly(0)) {
                analyticsLogger.logError(any())
            }
        }

    fun setUp(authenticationResult: Either<LoginError, Profile> = LoginError.UnknownError.left()) {
        everySuspend { sessionRepository.login(any(), any()) } returns authenticationResult
    }

    private fun ComposeUiTest.renderLoginScreen(
        openAuthenticatedScreen: () -> Unit = {},
        openCreateProfileScreen: () -> Unit = {},
        openForgotPasswordScreen: () -> Unit = {},
    ) {
        setContent {
            LoginScreen(
                viewModel = LoginViewModel(sessionRepository),
                analyticsLogger = analyticsLogger,
                openAuthenticatedScreen = openAuthenticatedScreen,
                openCreateProfileScreen = openCreateProfileScreen,
                openForgotPasswordScreen = openForgotPasswordScreen,
            )
        }
    }
}

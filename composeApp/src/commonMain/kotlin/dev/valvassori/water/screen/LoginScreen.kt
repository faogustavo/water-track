package dev.valvassori.water.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.valvassori.water.analytics.AnalyticsLogger
import dev.valvassori.water.components.ErrorMessage
import dev.valvassori.water.components.LoadingStateButton
import dev.valvassori.water.components.OrDivider
import dev.valvassori.water.components.input.PasswordInput
import dev.valvassori.water.components.input.UsernameInput
import dev.valvassori.water.components.screen.BaseScreenBody
import dev.valvassori.water.error.LoginError
import dev.valvassori.water.ext.defaultHorizontalPadding
import dev.valvassori.water.ext.messageOrNull
import dev.valvassori.water.helpers.State
import dev.valvassori.water.validator.UsernameValidator
import dev.valvassori.water.viewmodel.LoginViewModel
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.error_invalid_credentials
import watertrack.composeapp.generated.resources.error_network
import watertrack.composeapp.generated.resources.error_unknown
import watertrack.composeapp.generated.resources.login_button
import watertrack.composeapp.generated.resources.login_forgot_password_button
import watertrack.composeapp.generated.resources.login_sign_up_button
import watertrack.composeapp.generated.resources.login_subtitle
import watertrack.composeapp.generated.resources.login_title
import watertrack.composeapp.generated.resources.pana_drink

private const val PAGE_NAME = "Login"

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    analyticsLogger: AnalyticsLogger = koinInject { parametersOf(PAGE_NAME) },
    openAuthenticatedScreen: () -> Unit = {},
    openCreateProfileScreen: () -> Unit = {},
    openForgotPasswordScreen: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    val username by viewModel.username.collectAsState()
    var usernameFocused by remember { mutableStateOf(false) }
    val usernameValidation by viewModel.usernameValidation.collectAsState(UsernameValidator.ValidationState.Valid)
    val usernameValidationMessage: StringResource? by remember {
        derivedStateOf { usernameValidation.messageOrNull.takeIf { !usernameFocused } }
    }

    val password by viewModel.password.collectAsState()
    var passwordFocused by remember { mutableStateOf(false) }
    val passwordValidation by viewModel.passwordValidation.collectAsState()
    val passwordValidationMessage: StringResource? by remember {
        derivedStateOf { passwordValidation.messageOrNull.takeIf { !passwordFocused } }
    }

    val state by viewModel.state.collectAsState(State.Idle)
    val errorMessage: StringResource? by remember {
        derivedStateOf {
            when (val localState = state) {
                is State.Error ->
                    when (localState.error) {
                        LoginError.IncorrectCredentials -> Res.string.error_invalid_credentials
                        LoginError.NetworkError -> Res.string.error_network
                        LoginError.UnknownError -> Res.string.error_unknown
                    }

                else -> null
            }
        }
    }

    LaunchedEffect(PAGE_NAME) {
        analyticsLogger.logPageView()
    }

    LaunchedEffect(state) {
        when (state) {
            is State.Success -> {
                openAuthenticatedScreen()
            }

            else -> {}
        }
    }

    LaunchedEffect(errorMessage) {
        val localErrorMessage = errorMessage ?: return@LaunchedEffect
        analyticsLogger.logError(getString(localErrorMessage))
    }

    LaunchedEffect(usernameValidationMessage) {
        val usernameErrorMessage = usernameValidationMessage ?: return@LaunchedEffect
        analyticsLogger.logError(getString(usernameErrorMessage))
    }

    LaunchedEffect(passwordValidationMessage) {
        val passwordErrorMessage = passwordValidationMessage ?: return@LaunchedEffect
        analyticsLogger.logError(getString(passwordErrorMessage))
    }

    // Render UI
    BaseScreenBody(
        image = painterResource(Res.drawable.pana_drink),
        title = stringResource(Res.string.login_title),
        subtitle = stringResource(Res.string.login_subtitle),
        modifier = Modifier.testTag("Screen.Login"),
    ) {
        AnimatedContent(errorMessage) {
            if (it != null) {
                ErrorMessage(
                    message = stringResource(it),
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .defaultHorizontalPadding()
                            .padding(top = 16.dp),
                )
            }
        }

        UsernameInput(
            value = username,
            onValueChange = viewModel::updateUsername,
            errorMessage = usernameValidationMessage?.let { stringResource(it) },
            modifier =
                Modifier
                    .testTag("Authentication.Username")
                    .fillMaxWidth()
                    .defaultHorizontalPadding()
                    .padding(top = if (errorMessage != null) 4.dp else 16.dp)
                    .onFocusChanged { usernameFocused = it.isFocused },
        )

        PasswordInput(
            value = password,
            onValueChange = viewModel::updatePassword,
            keyboardActions =
                KeyboardActions {
                    focusManager.clearFocus(force = true)
                    viewModel.authenticate()
                },
            errorMessage = passwordValidationMessage?.let { stringResource(it) },
            modifier =
                Modifier
                    .testTag("Authentication.Password")
                    .fillMaxWidth()
                    .defaultHorizontalPadding()
                    .onFocusChanged { passwordFocused = it.isFocused },
        )

        LoadingStateButton(
            onClick = {
                focusManager.clearFocus(force = true)
                viewModel.authenticate()
            },
            text = stringResource(Res.string.login_button),
            isLoading = state is State.Loading,
            modifier =
                Modifier
                    .testTag("Authentication.Submit")
                    .fillMaxWidth()
                    .defaultHorizontalPadding()
                    .padding(top = 16.dp),
        )

        OrDivider()

        Button(
            onClick = openCreateProfileScreen,
            shape = RoundedCornerShape(8.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                ),
            modifier =
                Modifier
                    .testTag("Authentication.SignUp")
                    .fillMaxWidth()
                    .defaultHorizontalPadding()
                    .padding(top = 16.dp),
        ) {
            Text(stringResource(Res.string.login_sign_up_button))
        }

        TextButton(
            onClick = openForgotPasswordScreen,
            shape = RoundedCornerShape(8.dp),
            modifier =
                Modifier
                    .testTag("Authentication.ForgotPassword")
                    .fillMaxWidth()
                    .defaultHorizontalPadding(),
        ) {
            Text(stringResource(Res.string.login_forgot_password_button))
        }
    }
}

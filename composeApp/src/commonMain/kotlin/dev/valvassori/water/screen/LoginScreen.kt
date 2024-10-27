package dev.valvassori.water.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.valvassori.water.components.LoadingStateButton
import dev.valvassori.water.components.OrDivider
import dev.valvassori.water.components.input.PasswordInput
import dev.valvassori.water.components.input.UsernameInput
import dev.valvassori.water.components.screen.BaseScreenBody
import dev.valvassori.water.ext.defaultHorizontalPadding
import dev.valvassori.water.helpers.State
import dev.valvassori.water.viewmodel.LoginViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.login_button
import watertrack.composeapp.generated.resources.login_forgot_password_button
import watertrack.composeapp.generated.resources.login_sign_up_button
import watertrack.composeapp.generated.resources.login_subtitle
import watertrack.composeapp.generated.resources.login_title
import watertrack.composeapp.generated.resources.pana_drink

object LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = viewModel { LoginViewModel() }

        val username by viewModel.username.collectAsState()
        val password by viewModel.password.collectAsState()
        val state by viewModel.state.collectAsState(State.Idle)

        LaunchedEffect(state) {
            when (state) {
                is State.Error -> {
                    // TODO: Show Error
                }

                is State.Success -> {
                    navigator.replace(LoggedInScreen)
                }

                State.Loading, State.Idle -> {
                    // Do Nothing
                }
            }
        }

        BaseScreenBody(
            image = painterResource(Res.drawable.pana_drink),
            title = stringResource(Res.string.login_title),
            subtitle = stringResource(Res.string.login_subtitle),
            modifier = Modifier.testTag("Screen.Login"),
        ) {
            UsernameInput(
                value = username,
                onValueChange = viewModel::updateUsername,
                modifier =
                    Modifier
                        .testTag("Authentication.Username")
                        .fillMaxWidth()
                        .defaultHorizontalPadding()
                        .padding(top = 16.dp),
            )

            PasswordInput(
                value = password,
                onValueChange = viewModel::updatePassword,
                modifier =
                    Modifier
                        .testTag("Authentication.Password")
                        .fillMaxWidth()
                        .defaultHorizontalPadding(),
            )

            LoadingStateButton(
                onClick = viewModel::authenticate,
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
                onClick = {
                    navigator.push(CreateProfileScreen)
                },
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
                onClick = {
                    navigator.push(ForgotPasswordScreen())
                },
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
}

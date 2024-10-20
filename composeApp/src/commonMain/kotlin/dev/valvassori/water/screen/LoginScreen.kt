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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.valvassori.water.components.AuthenticationScreenBody
import dev.valvassori.water.components.OrDivider
import dev.valvassori.water.components.input.PasswordInput
import dev.valvassori.water.components.input.UsernameInput
import dev.valvassori.water.ext.defaultHorizontalPadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.login_button
import watertrack.composeapp.generated.resources.login_forgot_password_button
import watertrack.composeapp.generated.resources.login_sign_up_button
import watertrack.composeapp.generated.resources.login_subtitle
import watertrack.composeapp.generated.resources.login_title
import watertrack.composeapp.generated.resources.pana_drink

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        AuthenticationScreenBody(
            image = painterResource(Res.drawable.pana_drink),
            title = stringResource(Res.string.login_title),
            subtitle = stringResource(Res.string.login_subtitle),
        ) {
            UsernameInput(
                value = "",
                onValueChange = {},
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .defaultHorizontalPadding()
                        .padding(top = 16.dp),
            )

            PasswordInput(
                value = "",
                onValueChange = {},
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .defaultHorizontalPadding(),
            )

            Button(
                onClick = {},
                shape = RoundedCornerShape(8.dp),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .defaultHorizontalPadding()
                        .padding(top = 16.dp),
            ) {
                Text(stringResource(Res.string.login_button))
            }

            OrDivider()

            Button(
                onClick = {
                    navigator.push(CreateProfileScreen())
                },
                shape = RoundedCornerShape(8.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                modifier =
                    Modifier
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
                        .fillMaxWidth()
                        .defaultHorizontalPadding(),
            ) {
                Text(stringResource(Res.string.login_forgot_password_button))
            }
        }
    }
}

package dev.valvassori.water.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.valvassori.water.components.ButtonWithDesc
import dev.valvassori.water.components.OrDivider
import dev.valvassori.water.components.input.EmailInput
import dev.valvassori.water.components.screen.BaseScreenBody
import dev.valvassori.water.ext.defaultHorizontalPadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.forgot_password_info
import watertrack.composeapp.generated.resources.forgot_password_login_button
import watertrack.composeapp.generated.resources.forgot_password_login_desc
import watertrack.composeapp.generated.resources.forgot_password_submit
import watertrack.composeapp.generated.resources.forgot_password_title
import watertrack.composeapp.generated.resources.pana_forgot_password

class ForgotPasswordScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        BaseScreenBody(
            image = painterResource(Res.drawable.pana_forgot_password),
            title = stringResource(Res.string.forgot_password_title),
        ) {
            Text(
                text = stringResource(Res.string.forgot_password_info),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier =
                    Modifier
                        .fillMaxWidth(0.65f)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 4.dp),
            )

            EmailInput(
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
                        .padding(top = 8.dp),
            ) {
                Text(stringResource(Res.string.forgot_password_submit))
            }

            OrDivider()

            ButtonWithDesc(
                desc = stringResource(Res.string.forgot_password_login_desc),
                buttonText = stringResource(Res.string.forgot_password_login_button),
                onClick = {
                    navigator.popUntil { it is LoginScreen }
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .defaultHorizontalPadding(),
            )
        }
    }
}

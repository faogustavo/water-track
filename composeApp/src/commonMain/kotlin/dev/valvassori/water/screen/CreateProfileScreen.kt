package dev.valvassori.water.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.valvassori.water.components.AuthenticationScreenBody
import dev.valvassori.water.components.ButtonWithDesc
import dev.valvassori.water.components.OrDivider
import dev.valvassori.water.components.input.ConfirmPasswordInput
import dev.valvassori.water.components.input.EmailInput
import dev.valvassori.water.components.input.PasswordInput
import dev.valvassori.water.components.input.UsernameInput
import dev.valvassori.water.ext.defaultHorizontalPadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.create_profile_button
import watertrack.composeapp.generated.resources.create_profile_login_now_button
import watertrack.composeapp.generated.resources.create_profile_login_now_desc
import watertrack.composeapp.generated.resources.create_profile_subtitle
import watertrack.composeapp.generated.resources.create_profile_title
import watertrack.composeapp.generated.resources.pana_sign_up

class CreateProfileScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        AuthenticationScreenBody(
            image = painterResource(Res.drawable.pana_sign_up),
            title = stringResource(Res.string.create_profile_title),
            subtitle = stringResource(Res.string.create_profile_subtitle),
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

            EmailInput(
                value = "",
                onValueChange = {},
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .defaultHorizontalPadding(),
            )

            PasswordInput(
                value = "",
                onValueChange = {},
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .defaultHorizontalPadding(),
            )

            ConfirmPasswordInput(
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
                Text(stringResource(Res.string.create_profile_button))
            }

            OrDivider()

            ButtonWithDesc(
                desc = stringResource(Res.string.create_profile_login_now_desc),
                buttonText = stringResource(Res.string.create_profile_login_now_button),
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

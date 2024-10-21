package dev.valvassori.water.screen.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import dev.valvassori.water.components.config.ClickableConfig
import dev.valvassori.water.components.config.OnOffConfig
import dev.valvassori.water.ext.defaultHorizontalPadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.pana_avatar
import watertrack.composeapp.generated.resources.settings_hello
import watertrack.composeapp.generated.resources.tab_settings

object SettingsTab : Tab {
    override val options: TabOptions
        @Composable get() =
            TabOptions(
                index = 1u,
                title = stringResource(Res.string.tab_settings),
                icon = rememberVectorPainter(Icons.Default.Settings),
            )

    @Composable
    override fun Content() {
        Column {
            ProfileInfo()
            HorizontalDivider()
            NotificationConfig()
            SectionDivider()
            ThemeConfig()
            SectionDivider()
            AccountConfig()
        }
    }
}

@Composable
private fun ProfileInfo() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(vertical = 16.dp)
                .defaultHorizontalPadding(),
    ) {
        Image(
            painter = painterResource(Res.drawable.pana_avatar),
            contentDescription = null,
            modifier = Modifier.size(48.dp),
        )

        Column {
            Text(
                text = stringResource(Res.string.settings_hello, "johndoe95"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(start = 16.dp),
            )

            Text(
                text = "john@doe.com",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(start = 16.dp),
            )
        }
    }
}

@Composable
private fun SectionDivider() {
    HorizontalDivider(
        modifier =
            Modifier
                .padding(top = 8.dp, bottom = 16.dp),
    )
}

@Composable
private fun SectionTitle(
    text: String,
    modifier: Modifier = Modifier,
) = Text(
    text = text,
    style = MaterialTheme.typography.bodyMedium,
    fontWeight = FontWeight.SemiBold,
    color = MaterialTheme.colorScheme.primary,
    modifier = Modifier.defaultHorizontalPadding().then(modifier),
)

@Composable
private fun NotificationConfig() {
    SectionTitle("Notifications", modifier = Modifier.padding(top = 16.dp))

    OnOffConfig(
        title = "Enable Notifications",
        subtitle = "Notify when it's time to drink water",
        value = true,
        onChange = {},
    )

    ClickableConfig(
        title = "Notification",
        value = "2 hours after last drink",
        onClick = {},
    )

    ClickableConfig(
        title = "Notification window",
        value = "Everyday from 9:00 to 21:00",
        onClick = {},
    )
}

@Composable
private fun ThemeConfig() {
    SectionTitle("Theme")

    ClickableConfig(
        title = "App Theme",
        value = "Sync with system (Light/Dark)",
        onClick = {},
    )
}

@Composable
private fun AccountConfig() {
    SectionTitle("Account Config")

    ClickableConfig(
        title = "Change password",
        value = "Change your password",
        onClick = {},
    )

    ClickableConfig(
        title = "Delete my profile",
        value = "All data will be lost",
        onClick = {},
    )

    ClickableConfig(
        title = "Logout",
        value = "Logout from the app",
        onClick = {},
    )
}

package dev.valvassori.water.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import dev.valvassori.water.components.screen.BaseScreenBody
import dev.valvassori.water.screen.tabs.HomeTab
import dev.valvassori.water.screen.tabs.SettingsTab
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.water_track_title

object LoggedInScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val tabs = setOf(HomeTab, SettingsTab)

        TabNavigator(HomeTab) { navigator ->
            val selectedTabIndex = tabs.indexOfFirst { it == navigator.current }
            Scaffold(
                bottomBar = {
                    PrimaryTabRow(selectedTabIndex = selectedTabIndex) {
                        tabs.forEachIndexed { index, tab ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { navigator.current = tab },
                                text = { Text(tab.options.title) },
                                icon = { tab.options.icon?.let { Icon(it, null) } },
                            )
                        }
                    }
                },
            ) {
                BaseScreenBody(title = stringResource(Res.string.water_track_title)) {
                    CurrentTab()
                }
            }
        }
    }
}

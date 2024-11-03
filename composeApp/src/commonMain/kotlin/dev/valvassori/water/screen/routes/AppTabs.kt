package dev.valvassori.water.screen.routes

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.valvassori.water.components.screen.BaseScreenBody
import dev.valvassori.water.ext.popUpToRoot
import dev.valvassori.water.nav.Tab
import dev.valvassori.water.screen.tabs.HomeTab
import dev.valvassori.water.screen.tabs.SettingsTab
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.tab_home
import watertrack.composeapp.generated.resources.tab_settings
import watertrack.composeapp.generated.resources.water_track_title

@Serializable
data object AppTabs {
    @Serializable
    data object Home : Tab {
        override val title
            @Composable get() = stringResource(Res.string.tab_home)
        override val icon
            @Composable get() = rememberVectorPainter(Icons.Default.Home)
    }

    @Serializable
    data object Settings : Tab {
        override val title
            @Composable get() = stringResource(Res.string.tab_settings)
        override val icon
            @Composable get() = rememberVectorPainter(Icons.Default.Settings)
    }
}

fun NavGraphBuilder.registerAppRoutes(navController: NavController) {
    composable<AppTabs> {
        LoggedInScreen(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoggedInScreen(parentNavController: NavController) {
    val navController = rememberNavController()
    val tabs = remember { setOf(AppTabs.Home, AppTabs.Settings) }
    var selectedTab: Int by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            PrimaryTabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = index == selectedTab,
                        onClick = {
                            selectedTab = index
                            navController.navigate(tab) {
                                popUpToRoot(navController)
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        text = { Text(tab.title) },
                        icon = { Icon(tab.icon, null) },
                    )
                }
            }
        },
    ) {
        BaseScreenBody(
            title = stringResource(Res.string.water_track_title),
            modifier = Modifier.padding(it),
        ) {
            AppTabsRoutes(navController, parentNavController)
        }
    }
}

@Composable
private fun AppTabsRoutes(
    navController: NavHostController,
    parentNavController: NavController,
) {
    NavHost(navController, startDestination = AppTabs.Home) {
        composable<AppTabs.Home> {
            HomeTab()
        }
        composable<AppTabs.Settings> {
            SettingsTab()
        }
    }
}

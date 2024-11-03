package dev.valvassori.water

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.valvassori.water.screen.routes.AuthRoutes
import dev.valvassori.water.screen.routes.registerAppRoutes
import dev.valvassori.water.screen.routes.registerAuthRoutes
import dev.valvassori.water.theme.AppTheme

@Composable
fun App() {
    val navigationController = rememberNavController()

    AppTheme(
        materialYou = false, // TODO: Move to flag
    ) {
        AppNavHost(navigationController)
    }
}

@Composable
private fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = AuthRoutes) {
        registerAuthRoutes(navController)
        registerAppRoutes(navController)
    }
}

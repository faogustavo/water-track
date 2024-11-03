package dev.valvassori.water

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.valvassori.water.di.repositoryModule
import dev.valvassori.water.di.viewModelModule
import dev.valvassori.water.screen.routes.AuthRoutes
import dev.valvassori.water.screen.routes.registerAppRoutes
import dev.valvassori.water.screen.routes.registerAuthRoutes
import dev.valvassori.water.theme.AppTheme
import org.koin.compose.KoinApplication

@Composable
fun App() {
    val navigationController = rememberNavController()

    KoinApplication(
        application = {
            modules(
                viewModelModule,
                repositoryModule,
            )
        },
    ) {
        AppTheme(
            materialYou = false, // TODO: Move to flag
        ) {
            AppNavHost(navigationController)
        }
    }
}

@Composable
private fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = AuthRoutes) {
        registerAuthRoutes(navController)
        registerAppRoutes(navController)
    }
}

package dev.valvassori.water.screen.routes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.valvassori.water.components.BackButton
import dev.valvassori.water.ext.composableWithDefaultAnimation
import dev.valvassori.water.ext.popUpToRoot
import dev.valvassori.water.screen.CreateProfileScreen
import dev.valvassori.water.screen.ForgotPasswordScreen
import dev.valvassori.water.screen.LoginScreen
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data object AuthRoutes {
    @Serializable
    data object Login

    @Serializable
    data object CreateProfile

    @Serializable
    data object ForgotPassword
}

fun NavGraphBuilder.registerAuthRoutes(navController: NavController) {
    composable<AuthRoutes> {
        AuthScreen(navController)
    }
}

@Composable
private fun AuthScreen(parentNavController: NavController) {
    val navController = rememberNavController()
    val hasBackButton by navController.currentBackStack.map { it.size > 2 }.collectAsState(false)

    Scaffold(modifier = Modifier.testTag("App.Scaffold")) {
        Surface(modifier = Modifier.padding(it)) {
            AuthRoutesNav(navController, parentNavController)
            BackButton(
                visible = hasBackButton,
                onClick = { navController.popBackStack() },
            )
        }
    }
}

@Composable
private fun AuthRoutesNav(
    navController: NavHostController,
    parentNavController: NavController,
) {
    NavHost(navController, startDestination = AuthRoutes.Login) {
        composableWithDefaultAnimation<AuthRoutes.Login> {
            LoginScreen(
                openAuthenticatedScreen = {
                    parentNavController.navigate(AppTabs) {
                        popUpToRoot(parentNavController)
                    }
                },
                openCreateProfileScreen = { navController.navigate(AuthRoutes.CreateProfile) },
                openForgotPasswordScreen = { navController.navigate(AuthRoutes.ForgotPassword) },
            )
        }
        composableWithDefaultAnimation<AuthRoutes.ForgotPassword> {
            ForgotPasswordScreen(
                returnToLogin = {
                    navController.navigate(AuthRoutes.Login) {
                        popUpToRoot(navController)
                    }
                },
            )
        }
        composableWithDefaultAnimation<AuthRoutes.CreateProfile> {
            CreateProfileScreen(
                returnToLogin = {
                    navController.navigate(AuthRoutes.Login) {
                        popUpToRoot(navController)
                    }
                },
            )
        }
    }
}

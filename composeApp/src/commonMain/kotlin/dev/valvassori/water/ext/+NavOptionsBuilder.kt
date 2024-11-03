package dev.valvassori.water.ext

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder

fun NavOptionsBuilder.popUpToRoot(navController: NavController) {
    popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
}

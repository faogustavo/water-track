package dev.valvassori.water.ext

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

inline fun <reified T : Any> NavGraphBuilder.composableWithDefaultAnimation(
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) = composable<T>(
    content = content,
    enterTransition = {
        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)
    },
    popEnterTransition = {
        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End)
    },
)

package dev.valvassori.water.helpers

import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.StringResource

fun getString(
    stringResource: StringResource,
    vararg formatterArgs: String,
) = runBlocking {
    org.jetbrains.compose.resources
        .getString(stringResource, *formatterArgs)
}

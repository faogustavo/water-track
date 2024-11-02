package dev.valvassori.water.ext

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.compose.LocalLifecycleOwner
import dev.valvassori.water.App

@OptIn(ExperimentalTestApi::class)
fun ComposeUiTest.renderApp() {
    val testLifecycleOwner =
        object : LifecycleOwner {
            override val lifecycle: LifecycleRegistry = LifecycleRegistry(this)
        }

    runOnUiThread {
        testLifecycleOwner.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    setContent {
        CompositionLocalProvider(LocalLifecycleOwner provides testLifecycleOwner) {
            App()
        }
    }
}

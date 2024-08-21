package com.rafael.tictactoe.core.platform.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

class LifecycleEventsHandler {
    companion object {
        @Composable
        fun Handle(
            onStop: () -> Unit = {},
            onResume: () -> Unit = {},
            onDestroy: () -> Unit = {},
        ) {
            val lifecycleOwner = LocalLifecycleOwner.current
            val latestOnStop by rememberUpdatedState(onStop)
            val latestOnResume by rememberUpdatedState(onResume)
            val latestOnDestroy by rememberUpdatedState(onDestroy)

            DisposableEffect(lifecycleOwner) {
                val observer = LifecycleEventObserver { _, event ->
                    when (event) {
                        Lifecycle.Event.ON_STOP -> {
                            latestOnStop()
                        }

                        Lifecycle.Event.ON_RESUME -> {
                            latestOnResume()
                        }

                        Lifecycle.Event.ON_DESTROY -> {
                            latestOnDestroy()
                        }

                        else -> {
                            /* Do nothing */
                        }
                    }
                }

                lifecycleOwner.lifecycle.addObserver(observer)

                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(observer)
                }
            }
        }
    }
}

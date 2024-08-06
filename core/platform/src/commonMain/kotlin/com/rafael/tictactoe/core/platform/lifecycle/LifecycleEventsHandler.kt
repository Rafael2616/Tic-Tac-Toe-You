package com.rafael.tictactoe.core.platform.lifecycle

import androidx.compose.runtime.Composable

expect class LifecycleEventsHandler {
    companion object {
        @Composable
        fun Handle(
            onStop: () -> Unit = {},
            onResume: () -> Unit = {},
            onDestroy: () -> Unit = {},
        )
    }
}

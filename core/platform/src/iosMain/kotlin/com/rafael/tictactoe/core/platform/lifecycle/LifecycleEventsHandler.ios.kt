package com.rafael.tictactoe.core.platform.lifecycle

import androidx.compose.runtime.Composable

actual class LifecycleEventsHandler {
    actual companion object {
        @Composable
        actual fun Handle(
            onStop: () -> Unit,
            onResume: () -> Unit,
            onDestroy: () -> Unit,
        ) {
            // IOS doesn't have an lifecycle
        }
    }
}

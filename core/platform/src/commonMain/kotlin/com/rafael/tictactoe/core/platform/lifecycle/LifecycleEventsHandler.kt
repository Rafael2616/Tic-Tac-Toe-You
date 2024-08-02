package com.rafael.tictactoe.core.platform.lifecycle

import androidx.compose.runtime.Composable

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
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

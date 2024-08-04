package com.rafael.tictactoe.core.platform.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class LocalViewCurrent {
    actual companion object {
        actual val height: Int
            @Composable
            get() = LocalView.current.height
        actual val width: Int
            @Composable
            get() = LocalView.current.width
    }
}

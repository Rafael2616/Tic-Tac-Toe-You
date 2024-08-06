package com.rafael.tictactoe.core.platform.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

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

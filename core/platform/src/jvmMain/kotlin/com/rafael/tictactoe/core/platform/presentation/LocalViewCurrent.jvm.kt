package com.rafael.tictactoe.core.platform.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo

actual class LocalViewCurrent {
    actual companion object {
        @OptIn(ExperimentalComposeUiApi::class)
        actual val height: Int
            @Composable
            get() = LocalWindowInfo.current.containerSize.height
        @OptIn(ExperimentalComposeUiApi::class)
        actual val width: Int
            @Composable
            get() = LocalWindowInfo.current.containerSize.width
    }
}

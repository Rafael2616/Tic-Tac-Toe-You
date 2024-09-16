package com.rafael.tictactoe.core.platform.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo

@OptIn(ExperimentalComposeUiApi::class)
actual val isInLandscape: Boolean
    @Composable
    get() {
        val windowInfo = LocalWindowInfo.current
        return windowInfo.containerSize.width > windowInfo.containerSize.height
    }

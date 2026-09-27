package com.rafael.tictactoe.core.platform.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo

val isInLandscape: Boolean
    @Composable
    get() {
        val size = LocalWindowInfo.current.containerSize
        return size.width > size.height
    }

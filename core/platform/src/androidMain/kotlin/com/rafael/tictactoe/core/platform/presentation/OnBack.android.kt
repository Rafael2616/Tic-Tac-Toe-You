package com.rafael.tictactoe.core.platform.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun OnBack(action: () -> Unit) {
    BackHandler {
        action()
    }
}

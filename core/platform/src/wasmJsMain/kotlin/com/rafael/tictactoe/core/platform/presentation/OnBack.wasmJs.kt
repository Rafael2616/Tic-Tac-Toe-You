package com.rafael.tictactoe.core.platform.presentation

import androidx.compose.runtime.Composable

@Composable
actual fun OnBack(enabled: Boolean, action: () -> Unit) {
    if (enabled) {
        action()
    }
}

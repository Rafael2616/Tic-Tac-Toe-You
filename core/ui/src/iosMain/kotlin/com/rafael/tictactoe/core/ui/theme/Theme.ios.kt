package com.rafael.tictactoe.core.ui.theme

import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.settings.domain.model.SettingsState

@Composable
actual fun AppTheme(
    isSystemInDarkTheme: Boolean,
    state: SettingsState,
    content: @Composable () -> Unit,
) {
    content()

    TODO("AppTheme(onEvent, isSystemInDarkTheme, state, content) Not yet implemented on IOS")
}

package com.rafael.tictactoe.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.settings.domain.model.SettingsState

// Desktop doesn't have support for Dynamic colors yet
@Composable
actual fun AppTheme(
    isSystemInDarkTheme: Boolean,
    state: SettingsState,
    content: @Composable () -> Unit,
) {
    val isDarkThemeOn = state.isDarkThemeOn
    val isFollowingSystemOn = state.isFollowingSystemOn

    // Handle which color scheme will be applied
    val colors = if (isFollowingSystemOn) {
        if (isSystemInDarkTheme) {
            DarkColors
        } else {
            LightColors
        }
    } else {
        if (isDarkThemeOn) {
            DarkColors
        } else {
            LightColors
        }
    }

    MaterialTheme(
        colorScheme = colors,
        shapes = Shapes,
        typography = Typography,
        content = content,
    )
}

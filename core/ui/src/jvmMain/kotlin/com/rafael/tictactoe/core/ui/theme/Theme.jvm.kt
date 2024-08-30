package com.rafael.tictactoe.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import kotlinx.coroutines.delay
import org.jetbrains.skiko.currentSystemTheme

// Desktop doesn't have support for Dynamic colors yet
@Composable
actual fun AppTheme(
    isSystemInDarkTheme: Boolean,
    state: SettingsState,
    content: @Composable () -> Unit,
) {
    val isDarkThemeOn = state.isDarkThemeOn
    val isFollowingSystemOn = state.isFollowingSystemOn
    // This is necessary as compose 'isSystemInDarkTheme()' has a bug that not trigger the recomposition in jvm
    // See: https://youtrack.jetbrains.com/issue/CMP-5871/
    // Remove when the issue below is fixed
    var recomposableIsSystemInDarkTheme by remember { mutableStateOf( currentSystemTheme.name == "DARK" ) }

    // Handle which color scheme will be applied
    val colors = if (isFollowingSystemOn) {
        // Use isSystemInDarkTheme val when the mentioned bug is fixed
        if (recomposableIsSystemInDarkTheme) {
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

    // Manually check and update the value for system theme
    LaunchedEffect(isFollowingSystemOn) {
        while (isFollowingSystemOn) {
            recomposableIsSystemInDarkTheme = currentSystemTheme.name == "DARK"
            delay(1000L)
        }
    }
}

package com.rafael.tictactoe.feature.settings.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.dark_theme
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.templates.SwitchPreference

@Composable
fun DarkThemePreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    if (!state.isFollowingSystemOn) {
        SwitchPreference(
            title = Res.string.dark_theme,
            leadingIcon = if (state.isDarkThemeOn) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
            isChecked = state.isDarkThemeOn,
            onCheckedChange = { onEvent(SettingsEvent.ToggleIsDarkThemeOn) },
        )
    }
}

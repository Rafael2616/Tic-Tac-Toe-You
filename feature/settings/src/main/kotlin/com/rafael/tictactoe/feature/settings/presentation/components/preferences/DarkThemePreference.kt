package com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.SwitchPreference

@Composable
fun DarkThemePreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    if (!state.isFollowingSystemOn) {
        SwitchPreference(
            title = R.string.dark_theme,
            leadingIcon = if (state.isDarkThemeOn) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
            isChecked = state.isDarkThemeOn,
            onCheckedChange = { onEvent(SettingsEvent.ToggleIsDarkThemeOn) },
        )
    }
}

package com.rafael.tictactoe.settings.settingsscreen.presentation.components.groups

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.DarkThemePreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.DynamicColorsPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.FollowSystemPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.PreferenceGroup

@Composable
fun LazyItemScope.ThemePreferenceGroup(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    PreferenceGroup(heading = R.string.group_theme) {
        FollowSystemPreference(onEvent, state)
        DarkThemePreference(onEvent, state)
        DynamicColorsPreference(onEvent, state)
    }
}

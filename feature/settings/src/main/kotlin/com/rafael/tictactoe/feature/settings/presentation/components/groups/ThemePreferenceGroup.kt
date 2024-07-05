package com.rafael.tictactoe.feature.settings.presentation.components.groups

import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.DarkThemePreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.DynamicColorsPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.FollowSystemPreference
import com.rafael.tictactoe.feature.settings.presentation.components.templates.PreferenceGroup

@Composable
fun ThemePreferenceGroup(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    PreferenceGroup(heading = R.string.group_theme) {
        FollowSystemPreference(onEvent, state)
        DarkThemePreference(onEvent, state)
        DynamicColorsPreference(onEvent, state)
    }
}

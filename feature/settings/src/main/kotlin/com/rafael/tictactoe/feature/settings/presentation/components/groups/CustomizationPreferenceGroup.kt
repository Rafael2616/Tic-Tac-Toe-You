package com.rafael.tictactoe.settings.settingsscreen.presentation.components.groups

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.BoardCustomizationPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.CircleColorPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.WinnerLineColorPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.XColorPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.PreferenceGroup

@Composable
fun LazyItemScope.CustomizationPreferenceGroup(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    PreferenceGroup(heading = R.string.group_customization) {
        WinnerLineColorPreference(onEvent, state)
        CircleColorPreference(onEvent, state)
        XColorPreference(onEvent, state)
        BoardCustomizationPreference(onEvent, state)
    }
}

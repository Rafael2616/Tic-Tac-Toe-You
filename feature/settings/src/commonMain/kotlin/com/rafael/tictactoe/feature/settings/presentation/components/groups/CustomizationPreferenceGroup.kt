package com.rafael.tictactoe.feature.settings.presentation.components.groups

import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.group_customization
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.BoardCustomizationPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.CircleColorPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.WinnerLineColorPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.XColorPreference
import com.rafael.tictactoe.feature.settings.presentation.components.templates.PreferenceGroup

@Composable
fun CustomizationPreferenceGroup(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    PreferenceGroup(heading = Res.string.group_customization) {
        WinnerLineColorPreference(onEvent, state)
        CircleColorPreference(onEvent, state)
        XColorPreference(onEvent, state)
        BoardCustomizationPreference(onEvent, state)
    }
}

package com.rafael.tictactoe.settings.settingsscreen.presentation.components.groups

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.BackgroundMusicPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.SoundEffectsPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.PreferenceGroup

@Composable
fun LazyItemScope.SoundPreferenceGroup(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    PreferenceGroup(heading = R.string.group_sound) {
        BackgroundMusicPreference(onEvent, state)
        SoundEffectsPreference(onEvent, state)
    }
}

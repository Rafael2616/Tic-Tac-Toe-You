package com.rafael.tictactoe.feature.settings.presentation.components.groups

import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.group_sound
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.BackgroundMusicPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.SoundEffectsPreference
import com.rafael.tictactoe.feature.settings.presentation.components.templates.PreferenceGroup

@Composable
fun SoundPreferenceGroup(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    PreferenceGroup(heading = Res.string.group_sound) {
        BackgroundMusicPreference(onEvent, state)
        SoundEffectsPreference(onEvent, state)
    }
}

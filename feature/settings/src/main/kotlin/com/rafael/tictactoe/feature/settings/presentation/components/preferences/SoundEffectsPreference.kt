package com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.MusicOff
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.SwitchPreference

@Composable
fun SoundEffectsPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    SwitchPreference(
        title = R.string.sound_effects,
        leadingIcon = if (state.isSoundEffectsOn) Icons.Outlined.MusicNote else Icons.Outlined.MusicOff,
        isChecked = state.isSoundEffectsOn,
        onCheckedChange = { onEvent(SettingsEvent.ToggleIsSoundEffectsOn) },
    )
}

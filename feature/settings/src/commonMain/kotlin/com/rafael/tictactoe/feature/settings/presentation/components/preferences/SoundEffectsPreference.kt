package com.rafael.tictactoe.feature.settings.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.MusicOff
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.sound_effects
import com.rafael.tictactoe.core.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.templates.SwitchPreference

@Composable
fun SoundEffectsPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    SwitchPreference(
        title = Res.string.sound_effects,
        leadingIcon = if (state.isSoundEffectsOn) Icons.Outlined.MusicNote else Icons.Outlined.MusicOff,
        isChecked = state.isSoundEffectsOn,
        onCheckedChange = { onEvent(SettingsEvent.ToggleIsSoundEffectsOn) },
    )
}

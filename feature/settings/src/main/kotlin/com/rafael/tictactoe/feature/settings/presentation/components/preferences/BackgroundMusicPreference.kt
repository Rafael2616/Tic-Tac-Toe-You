package com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.VolumeOff
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.SwitchPreference

@Composable
fun BackgroundMusicPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    SwitchPreference(
        title = R.string.background_music,
        leadingIcon = if (state.isBackgroundMusicOn) Icons.AutoMirrored.Outlined.VolumeUp else Icons.AutoMirrored.Outlined.VolumeOff,
        isChecked = state.isBackgroundMusicOn,
        onCheckedChange = { onEvent(SettingsEvent.ToggleIsBackgroundMusicOn) },
    )
}

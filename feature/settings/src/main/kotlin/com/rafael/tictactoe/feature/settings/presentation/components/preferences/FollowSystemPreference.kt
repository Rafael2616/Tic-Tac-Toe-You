package com.rafael.tictactoe.feature.settings.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrightnessMedium
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.templates.SwitchPreference

@Composable
fun FollowSystemPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    SwitchPreference(
        title = R.string.system_theme,
        leadingIcon = Icons.Outlined.BrightnessMedium,
        isChecked = state.isFollowingSystemOn,
        onCheckedChange = { onEvent(SettingsEvent.ToggleIsFollowingSystemOn) },
    )
}

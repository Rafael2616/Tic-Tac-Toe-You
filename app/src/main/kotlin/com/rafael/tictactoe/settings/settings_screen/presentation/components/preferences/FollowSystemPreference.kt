package com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrightnessMedium
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.SwitchPreference

@Composable
fun FollowSystemPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    val isBellowA12 = VERSION.SDK_INT < VERSION_CODES.S

    SwitchPreference(
        title = R.string.system_theme,
        leadingIcon = Icons.Outlined.BrightnessMedium,
        isChecked = state.isFollowingSystemOn,
        onCheckedChange = { onEvent(SettingsEvent.ToggleIsFollowingSystemOn) },
        isLastPref = state.isFollowingSystemOn && isBellowA12,
    )
}

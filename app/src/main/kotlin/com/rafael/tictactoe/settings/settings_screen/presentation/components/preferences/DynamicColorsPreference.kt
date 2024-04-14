package com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.SwitchPreference

@Composable
fun DynamicColorsPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    if (VERSION.SDK_INT >= VERSION_CODES.S) {
        SwitchPreference(
            title = R.string.material_you,
            leadingIcon = Icons.Outlined.Palette,
            isChecked = state.isDynamicColorsOn,
            onCheckedChange = { onEvent(SettingsEvent.ToggleIsDynamicColorsOn) },
            isLastPref = true,
        )
    }
}

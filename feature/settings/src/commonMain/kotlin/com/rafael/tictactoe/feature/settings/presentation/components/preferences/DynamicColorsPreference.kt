package com.rafael.tictactoe.feature.settings.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.core.platform.presentation.dynamicColorsAvailability
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.material_you
import com.rafael.tictactoe.core.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.templates.SwitchPreference

@Composable
fun DynamicColorsPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    if (dynamicColorsAvailability) {
        SwitchPreference(
            title = Res.string.material_you,
            leadingIcon = Icons.Outlined.Palette,
            isChecked = state.isDynamicColorsOn,
            onCheckedChange = { onEvent(SettingsEvent.ToggleIsDynamicColorsOn) },
        )
    }
}

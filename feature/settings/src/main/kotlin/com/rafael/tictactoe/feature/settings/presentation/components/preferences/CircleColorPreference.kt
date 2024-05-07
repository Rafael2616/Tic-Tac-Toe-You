package com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Customization
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.IconPreference

@Composable
fun CircleColorPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    val circleColor by animateColorAsState(state.circleColor)

    IconPreference(
        title = R.string.color_circle,
        leadingCanva = {
            drawCircle(
                center = Offset(14.dp.toPx(), 14.dp.toPx()),
                radius = 12.dp.toPx(),
                style = Stroke(width = 5.dp.toPx()),
                color = circleColor,
            )
        },
        trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
        onClick = {
            onEvent(SettingsEvent.SetCustomizationType(Customization.CIRCLECOLOR))
            onEvent(SettingsEvent.ShowColorSelectionDialog(true))
        },
    )
}

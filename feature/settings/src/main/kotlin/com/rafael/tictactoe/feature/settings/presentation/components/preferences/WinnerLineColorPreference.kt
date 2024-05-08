package com.rafael.tictactoe.feature.settings.presentation.components.preferences

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.settings.domain.enums.Customization
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.templates.IconPreference

@Composable
fun WinnerLineColorPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
) {
    val winnerLineColor by animateColorAsState(state.winnerLineColor)

    IconPreference(
        title = R.string.winner_line_color,
        leadingCanva = {
            drawLine(
                strokeWidth = 5.dp.toPx(),
                start = Offset(x = 26.dp.toPx(), y = 2.dp.toPx()),
                end = Offset(x = 2.dp.toPx(), y = 26.dp.toPx()),
                color = winnerLineColor,
            )
        },
        trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
        onClick = {
            onEvent(SettingsEvent.SetCustomizationType(Customization.WINNERLINECOLOR))
            onEvent(SettingsEvent.ShowColorSelectionDialog(true))
        },
    )
}

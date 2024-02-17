package com.rafael.tictactoe.settings.settingsScreen.domain.useCases

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Customization
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent

fun colorSelectionUseCase(
    customization: Customization,
    option: Color,
    index: Int,
    onEvent: (SettingsEvent) -> Unit,
) {
    when (customization) {
        Customization.WINNERLINECOLOR -> {
            onEvent(SettingsEvent.SetWinnerLineColor(option))
            onEvent(SettingsEvent.SelectedWinnerLineColor(index))
        }
        Customization.XCOLOR -> {
            onEvent(SettingsEvent.SetXColor(option))
            onEvent(SettingsEvent.SelectedCircleColor(index))
        }
        Customization.CIRCLECOLOR -> {
            onEvent(SettingsEvent.SetCircleColor(option))
            onEvent(SettingsEvent.SelectedXColor(index))
        }
    }
}

package com.rafael.tictactoe.settings.domain.use_cases

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.domain.model.SettingsEvent

fun colorSelectionUseCase(
    customization: Customization,
    option: Int,
    onEvent: (SettingsEvent) -> Unit
) {

    fun Color(option: Int): Color {
        val color = Colors.get()

        return when (option) {
            0 -> color.blue
            1 -> color.red
            2 -> color.yellow
            3 -> color.dark_green
            4 -> color.light_green
            5 -> color.purple
            6 -> color.magenta
            7 -> color.white
            8 -> color.cyan
            else -> color.transparent
        }
    }

    when (customization) {
        Customization.WINNERLINECOLOR -> onEvent(SettingsEvent.SetWinnerLineColor(Color(option)))
        Customization.XCOLOR -> onEvent(SettingsEvent.SetXColor(Color(option)))
        Customization.CIRCLECOLOR ->  onEvent(SettingsEvent.SetCircleColor(Color(option)))
    }
}
package com.rafael.tictactoe.settings.settingsScreen.domain.useCases

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Colors
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Customization
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.IColor
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent

fun colorSelectionUseCase(
    customization: Customization,
    option: IColor,
    onEvent: (SettingsEvent) -> Unit,
) {
    when (customization) {
        Customization.WINNERLINECOLOR -> onEvent(SettingsEvent.SetWinnerLineColor(Color(option)))
        Customization.XCOLOR -> onEvent(SettingsEvent.SetXColor(Color(option)))
        Customization.CIRCLECOLOR -> onEvent(SettingsEvent.SetCircleColor(Color(option)))
    }
}

fun Color(option: IColor): Color {
    val color = Colors.get()

    return when (option) {
        IColor.BLUE -> color.blue
        IColor.RED -> color.red
        IColor.YELLOW -> color.yellow
        IColor.BURNT_YELLOW -> color.burnt_yellow
        IColor.DARK_GREEN -> color.dark_green
        IColor.LIGHT_GREEN -> color.light_green
        IColor.PURPLE -> color.purple
        IColor.MAGENTA -> color.magenta
        IColor.WHITE -> color.white
        IColor.CYAN -> color.cyan
    }
}

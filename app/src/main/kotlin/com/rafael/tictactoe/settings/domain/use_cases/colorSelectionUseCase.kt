package com.rafael.tictactoe.settings.domain.use_cases

import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.domain.model.SettingsEvent

fun colorSelectionUseCase(
    customization: Customization,
    option: Int,
    onEvent: (SettingsEvent) -> Unit
) {
    val color = Colors.get()

    fun setWinnerLineColor(option: Int) {
        when (option) {
            0 -> onEvent(SettingsEvent.SetWinnerLineColor(color.blue))
            1 -> onEvent(SettingsEvent.SetWinnerLineColor(color.red))
            2 -> onEvent(SettingsEvent.SetWinnerLineColor(color.yellow))
            3 -> onEvent(SettingsEvent.SetWinnerLineColor(color.dark_green))
            4 -> onEvent(SettingsEvent.SetWinnerLineColor(color.light_green))
            5 -> onEvent(SettingsEvent.SetWinnerLineColor(color.purple))
            6 -> onEvent(SettingsEvent.SetWinnerLineColor(color.magenta))
            7 -> onEvent(SettingsEvent.SetWinnerLineColor(color.white))
            8 -> onEvent(SettingsEvent.SetWinnerLineColor(color.cyan))
            else -> onEvent(SettingsEvent.SetWinnerLineColor(color.transparent))
        }
    }

    fun setXColor(option: Int) {
        when (option) {
            0 -> onEvent(SettingsEvent.SetXColor(color.blue))
            1 -> onEvent(SettingsEvent.SetXColor(color.red))
            2 -> onEvent(SettingsEvent.SetXColor(color.yellow))
            3 -> onEvent(SettingsEvent.SetXColor(color.dark_green))
            4 -> onEvent(SettingsEvent.SetXColor(color.light_green))
            5 -> onEvent(SettingsEvent.SetXColor(color.purple))
            6 -> onEvent(SettingsEvent.SetXColor(color.magenta))
            7 -> onEvent(SettingsEvent.SetXColor(color.white))
            8 -> onEvent(SettingsEvent.SetXColor(color.cyan))
            else -> onEvent(SettingsEvent.SetXColor(color.transparent))
        }
    }

    fun setCircleColor(option: Int) {
        when (option) {
            0 -> onEvent(SettingsEvent.SetCircleColor(color.blue))
            1 -> onEvent(SettingsEvent.SetCircleColor(color.red))
            2 -> onEvent(SettingsEvent.SetCircleColor(color.yellow))
            3 -> onEvent(SettingsEvent.SetCircleColor(color.dark_green))
            4 -> onEvent(SettingsEvent.SetCircleColor(color.light_green))
            5 -> onEvent(SettingsEvent.SetCircleColor(color.purple))
            6 -> onEvent(SettingsEvent.SetCircleColor(color.magenta))
            7 -> onEvent(SettingsEvent.SetCircleColor(color.white))
            8 -> onEvent(SettingsEvent.SetCircleColor(color.cyan))
            else -> onEvent(SettingsEvent.SetCircleColor(color.transparent))
        }
    }

    when (customization) {
        Customization.WINNERLINECOLOR -> setWinnerLineColor(option)
        Customization.XCOLOR -> setXColor(option)
        Customization.CIRCLECOLOR -> setCircleColor(option)
    }
}
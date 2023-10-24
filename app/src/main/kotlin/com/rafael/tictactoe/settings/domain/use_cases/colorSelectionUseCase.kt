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
            0 -> onEvent(SettingsEvent.setWinnerLineColor(color.blue))
            1 -> onEvent(SettingsEvent.setWinnerLineColor(color.red))
            2 -> onEvent(SettingsEvent.setWinnerLineColor(color.yellow))
            3 -> onEvent(SettingsEvent.setWinnerLineColor(color.dark_green))
            4 -> onEvent(SettingsEvent.setWinnerLineColor(color.light_green))
            5 -> onEvent(SettingsEvent.setWinnerLineColor(color.purple))
            6 -> onEvent(SettingsEvent.setWinnerLineColor(color.magenta))
            7 -> onEvent(SettingsEvent.setWinnerLineColor(color.white))
            8 -> onEvent(SettingsEvent.setWinnerLineColor(color.cyan))
            else -> onEvent(SettingsEvent.setWinnerLineColor(color.transparent))
        }
    }

    fun setXColor(option: Int) {
        when (option) {
            0 -> onEvent(SettingsEvent.setXColor(color.blue))
            1 -> onEvent(SettingsEvent.setXColor(color.red))
            2 -> onEvent(SettingsEvent.setXColor(color.yellow))
            3 -> onEvent(SettingsEvent.setXColor(color.dark_green))
            4 -> onEvent(SettingsEvent.setXColor(color.light_green))
            5 -> onEvent(SettingsEvent.setXColor(color.purple))
            6 -> onEvent(SettingsEvent.setXColor(color.magenta))
            7 -> onEvent(SettingsEvent.setXColor(color.white))
            8 -> onEvent(SettingsEvent.setXColor(color.cyan))
            else -> onEvent(SettingsEvent.setXColor(color.transparent))
        }
    }

    fun setCircleColor(option: Int) {
        when (option) {
            0 -> onEvent(SettingsEvent.setCircleColor(color.blue))
            1 -> onEvent(SettingsEvent.setCircleColor(color.red))
            2 -> onEvent(SettingsEvent.setCircleColor(color.yellow))
            3 -> onEvent(SettingsEvent.setCircleColor(color.dark_green))
            4 -> onEvent(SettingsEvent.setCircleColor(color.light_green))
            5 -> onEvent(SettingsEvent.setCircleColor(color.purple))
            6 -> onEvent(SettingsEvent.setCircleColor(color.magenta))
            7 -> onEvent(SettingsEvent.setCircleColor(color.white))
            8 -> onEvent(SettingsEvent.setCircleColor(color.cyan))
            else -> onEvent(SettingsEvent.setCircleColor(color.transparent))
        }
    }

    when (customization) {
        Customization.WINNERLINECOLOR -> setWinnerLineColor(option)
        Customization.XCOLOR -> setXColor(option)
        Customization.CIRCLECOLOR -> setCircleColor(option)
    }
}
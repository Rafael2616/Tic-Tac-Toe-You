package com.rafael.tictactoe.settings.domain.use_cases

import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun colorSelectionUseCase(
    customization: Customization,
    option: Int,
	settingsVM: SettingsViewModel
) {
    val color = Colors.get()

    fun setWinnerLineColor(option: Int) {
        when (option) {
            0 -> settingsVM.setWinnerLineColor(color.blue)
            1 -> settingsVM.setWinnerLineColor(color.red)
            2 -> settingsVM.setWinnerLineColor(color.yellow)
            3 -> settingsVM.setWinnerLineColor(color.dark_green)
            4 -> settingsVM.setWinnerLineColor(color.light_green)
            5 -> settingsVM.setWinnerLineColor(color.purple)
            6 -> settingsVM.setWinnerLineColor(color.magenta)
            7 -> settingsVM.setWinnerLineColor(color.white)
            8 -> settingsVM.setWinnerLineColor(color.cyan)
            else -> settingsVM.setWinnerLineColor(color.transparent)
        }
    }

    fun setXColor(option: Int) {
        when (option) {
            0 -> settingsVM.setXColor(color.blue)
            1 -> settingsVM.setXColor(color.red)
            2 -> settingsVM.setXColor(color.yellow)
            3 -> settingsVM.setXColor(color.dark_green)
            4 -> settingsVM.setXColor(color.light_green)
            5 -> settingsVM.setXColor(color.purple)
            6 -> settingsVM.setXColor(color.magenta)
            7 -> settingsVM.setXColor(color.white)
            8 -> settingsVM.setXColor(color.cyan)
            else -> settingsVM.setXColor(color.transparent)
        }
    }

    fun setCircleColor(option: Int) {
        when (option) {
            0 -> settingsVM.setCircleColor(color.blue)
            1 -> settingsVM.setCircleColor(color.red)
            2 -> settingsVM.setCircleColor(color.yellow)
            3 -> settingsVM.setCircleColor(color.dark_green)
            4 -> settingsVM.setCircleColor(color.light_green)
            5 -> settingsVM.setCircleColor(color.purple)
            6 -> settingsVM.setCircleColor(color.magenta)
            7 -> settingsVM.setCircleColor(color.white)
            8 -> settingsVM.setCircleColor(color.cyan)
            else -> settingsVM.setCircleColor(color.transparent)
        }
    }

    when (customization) {
        Customization.WINNERLINECOLOR -> setWinnerLineColor(option)
        Customization.XCOLOR -> setXColor(option)
        Customization.CIRCLECOLOR -> setCircleColor(option)
    }
}
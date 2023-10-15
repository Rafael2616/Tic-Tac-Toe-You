package com.rafael.tictactoe.settings.domain.use_cases

import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun lineColorSelectionUseCase(
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

    fun setColorX(option: Int) {
        when (option) {
            0 -> settingsVM.setColorX(color.blue)
            1 -> settingsVM.setColorX(color.red)
            2 -> settingsVM.setColorX(color.yellow)
            3 -> settingsVM.setColorX(color.dark_green)
            4 -> settingsVM.setColorX(color.light_green)
            5 -> settingsVM.setColorX(color.purple)
            6 -> settingsVM.setColorX(color.magenta)
            7 -> settingsVM.setColorX(color.white)
            8 -> settingsVM.setColorX(color.cyan)
            else -> settingsVM.setColorX(color.transparent)
        }
    }

    fun setColorCircle(option: Int) {
        when (option) {
            0 -> settingsVM.setColorCircle(color.blue)
            1 -> settingsVM.setColorCircle(color.red)
            2 -> settingsVM.setColorCircle(color.yellow)
            3 -> settingsVM.setColorCircle(color.dark_green)
            4 -> settingsVM.setColorCircle(color.light_green)
            5 -> settingsVM.setColorCircle(color.purple)
            6 -> settingsVM.setColorCircle(color.magenta)
            7 -> settingsVM.setColorCircle(color.white)
            8 -> settingsVM.setColorCircle(color.cyan)
            else -> settingsVM.setColorCircle(color.transparent)
        }
    }

    when (customization) {
        Customization.WINNERLINECOLOR -> setWinnerLineColor(option)
        Customization.COLORX -> setColorX(option)
        Customization.COLORCIRCLE -> setColorCircle(option)
    }
}
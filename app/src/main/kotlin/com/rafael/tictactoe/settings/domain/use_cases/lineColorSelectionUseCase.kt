package com.rafael.tictactoe.settings.domain.use_cases

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.settings.domain.util.Customization
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun lineColorSelectionUseCase(
    customization: Customization,
    option: Int,
	settingsVM: SettingsViewModel
) {

    fun setWinnerLineColor(option: Int) {
        when (option) {
            0 -> settingsVM.setWinnerLineColor(Color.Blue)
            1 -> settingsVM.setWinnerLineColor(Color.Red)
            2 -> settingsVM.setWinnerLineColor(Color.Yellow)
            3 -> settingsVM.setWinnerLineColor(Color.Green)
            4 -> settingsVM.setWinnerLineColor(Color(0xFF6400EE))
            5 -> settingsVM.setWinnerLineColor(Color.Magenta)
            6 -> settingsVM.setWinnerLineColor(Color.White)
            7 -> settingsVM.setWinnerLineColor(Color.Cyan)
            else -> settingsVM.setWinnerLineColor(Color.Transparent)
        }
    }

    fun setColorX(option: Int) {
        when (option) {
            0 -> settingsVM.setColorX(Color.Blue)
            1 -> settingsVM.setColorX(Color.Red)
            2 -> settingsVM.setColorX(Color.Yellow)
            3 -> settingsVM.setColorX(Color.Green)
            4 -> settingsVM.setColorX(Color(0xFF6400EE))
            5 -> settingsVM.setColorX(Color.Magenta)
            6 -> settingsVM.setColorX(Color.White)
            7 -> settingsVM.setColorX(Color.Cyan)
            else -> settingsVM.setColorX(Color.Transparent)
        }
    }

    fun setColorO(option: Int) {
        when (option) {
            0 -> settingsVM.setColorO(Color.Blue)
            1 -> settingsVM.setColorO(Color.Red)
            2 -> settingsVM.setColorO(Color.Yellow)
            3 -> settingsVM.setColorO(Color.Green)
            4 -> settingsVM.setColorO(Color(0xFF6400EE))
            5 -> settingsVM.setColorO(Color.Magenta)
            6 -> settingsVM.setColorO(Color.White)
            7 -> settingsVM.setColorO(Color.Cyan)
            else -> settingsVM.setColorO(Color.Transparent)
        }
    }

    when (customization) {
        Customization.WINNERLINECOLOR -> setWinnerLineColor(option)
        Customization.XCOLOR -> setColorX(option)
        Customization.OCOLOR -> setColorO(option)
    }
}
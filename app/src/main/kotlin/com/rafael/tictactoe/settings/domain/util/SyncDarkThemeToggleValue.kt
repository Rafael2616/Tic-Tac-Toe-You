package com.rafael.tictactoe.settings.domain.util

import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun SyncDarkThemeToggleValue(
    vm: SettingsViewModel,
    isSystemInDarkTheme: Boolean
) {

    if (isSystemInDarkTheme) {
        vm.updateIsDarkThemeOn(true)
    } else {
        vm.updateIsDarkThemeOn(false)
    }
}
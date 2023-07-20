package com.rafael.tictactoe.settings.domain.util

import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun SyncDarkThemeToggleValue(
viewModel: SettingsViewModel,
isFollowingSystemOn: Boolean,
isSystemInDarkTheme: Boolean
) {

    if (isFollowingSystemOn) {
        if (isSystemInDarkTheme) {
            viewModel.updateIsDarkThemeOn(true)
        } else {
            viewModel.updateIsDarkThemeOn(false)
        }
    }
}
package com.rafael.tictactoe.settings.domain.model

sealed interface SettingsGroup {
    object Theme : SettingsGroup
    object Sound : SettingsGroup
    object Customization : SettingsGroup
    object Other : SettingsGroup
}
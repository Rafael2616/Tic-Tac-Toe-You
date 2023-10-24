package com.rafael.tictactoe.settings.domain.model

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.settings.domain.enums.Customization

sealed interface SettingsEvent {
    object toggleIsBackgroundMusicOn : SettingsEvent
    object toggleIsSoundEffectsOn : SettingsEvent
    data class toggleIsDarkThemeOn(val value: Boolean, val withSound: Boolean = true) : SettingsEvent
    object toggleIsFollowingSystemOn : SettingsEvent
    object toggleIsDynamicColorsOn : SettingsEvent
    object onSettingsScreenDestroyment : SettingsEvent
    data class updateIsAboutShown(val value: Boolean): SettingsEvent
    data class setCustomizationType(val type: Customization): SettingsEvent
    data class showColorSelectionDialog(val value: Boolean): SettingsEvent
    data class setWinnerLineColor(val color: Color): SettingsEvent
    data class setXColor(val color: Color): SettingsEvent
    data class setCircleColor(val color: Color): SettingsEvent
    data class selectedWinnerLineColor(val value: Int): SettingsEvent
    data class selectedXColor(val value: Int): SettingsEvent
    data class selectedCircleColor(val value: Int): SettingsEvent
    data class isBGMusicPlaying(val value: Boolean): SettingsEvent
}
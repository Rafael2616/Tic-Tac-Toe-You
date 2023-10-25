package com.rafael.tictactoe.settings.domain.model

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.settings.domain.enums.Customization

sealed interface SettingsEvent {
    object ToggleIsBackgroundMusicOn : SettingsEvent
    object ToggleIsSoundEffectsOn : SettingsEvent
    object ToggleIsDarkThemeOn : SettingsEvent
    object ToggleIsFollowingSystemOn : SettingsEvent
    object ToggleIsDynamicColorsOn : SettingsEvent
    data class SetIsDarkThemeOn(val value: Boolean) : SettingsEvent
    object OnSettingsScreenDestroyment : SettingsEvent
    data class ShowAboutAppCard(val value: Boolean): SettingsEvent
    data class SetCustomizationType(val type: Customization): SettingsEvent
    data class ShowColorSelectionDialog(val value: Boolean): SettingsEvent
    data class SetWinnerLineColor(val color: Color): SettingsEvent
    data class SetXColor(val color: Color): SettingsEvent
    data class SetCircleColor(val color: Color): SettingsEvent
    data class SelectedWinnerLineColor(val value: Int): SettingsEvent
    data class SelectedXColor(val value: Int): SettingsEvent
    data class SelectedCircleColor(val value: Int): SettingsEvent
    data class IsBGMusicPlaying(val value: Boolean): SettingsEvent
}
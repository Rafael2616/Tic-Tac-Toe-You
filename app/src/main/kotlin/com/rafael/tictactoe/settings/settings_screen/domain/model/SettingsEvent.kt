package com.rafael.tictactoe.settings.settingsScreen.domain.model

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Customization

sealed interface SettingsEvent {
    object ToggleIsBackgroundMusicOn : SettingsEvent
    object ToggleIsSoundEffectsOn : SettingsEvent
    object ToggleIsDarkThemeOn : SettingsEvent
    object ToggleIsFollowingSystemOn : SettingsEvent
    object ToggleIsDynamicColorsOn : SettingsEvent
    data class SetIsDarkThemeOn(val value: Boolean) : SettingsEvent
    object OnSettingsScreenDestroyment : SettingsEvent
    data class ShowAboutAppCard(val value: Boolean) : SettingsEvent
    data class SetCustomizationType(val type: Customization) : SettingsEvent
    data class ShowColorSelectionDialog(val value: Boolean) : SettingsEvent
    data class SetWinnerLineColor(val color: Color) : SettingsEvent
    data class SetXColor(val color: Color) : SettingsEvent
    data class SetCircleColor(val color: Color) : SettingsEvent
    data class SelectedWinnerLineColor(val value: Int) : SettingsEvent
    data class SelectedXColor(val value: Int) : SettingsEvent
    data class SelectedCircleColor(val value: Int) : SettingsEvent
    data class StartBackgroundMusic(val value: Boolean) : SettingsEvent
    object ShowWhatsNewDialog : SettingsEvent
    object HideWhatsNewDialog : SettingsEvent
    object ExecUIClickSound : SettingsEvent
    object ToggleShowProfilePictureMenu : SettingsEvent
    object HideProfilePictureMenu : SettingsEvent
    data class ShowBoardCustomizationDialog(val value: Boolean) : SettingsEvent
    data class SetBoardShape(val shape: BoardShape, val index: Int) : SettingsEvent
    object ResetBoardParams : SettingsEvent
    data class UpdateBoardCardsBorderColor(val color: Color) : SettingsEvent
}

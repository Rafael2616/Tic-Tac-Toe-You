package com.rafael.tictactoe.feature.settings.domain.model

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.feature.settings.domain.enums.Customization

sealed interface SettingsEvent {
    data object ToggleIsBackgroundMusicOn : SettingsEvent
    data object ToggleIsSoundEffectsOn : SettingsEvent
    data object ToggleIsDarkThemeOn : SettingsEvent
    data object ToggleIsFollowingSystemOn : SettingsEvent
    data object ToggleIsDynamicColorsOn : SettingsEvent
    data class SetIsDarkThemeOn(val value: Boolean) : SettingsEvent
    data object OnSettingsScreenDestroy : SettingsEvent
    data class ShowAboutAppCard(val value: Boolean) : SettingsEvent
    data class SetCustomizationType(val type: Customization) : SettingsEvent
    data class ShowColorSelectionDialog(val value: Boolean) : SettingsEvent
    data class SetWinnerLineColor(val color: Color) : SettingsEvent
    data class SetXColor(val color: Color) : SettingsEvent
    data class SetCircleColor(val color: Color) : SettingsEvent
    data class SelectedWinnerLineColor(val value: Int) : SettingsEvent
    data class SelectedXColor(val value: Int) : SettingsEvent
    data class SelectedCircleColor(val value: Int) : SettingsEvent
    data class StartBackgroundMusic(val isAppInOnStop: Boolean) : SettingsEvent
    data object ShowWhatsNewDialog : SettingsEvent
    data object HideWhatsNewDialog : SettingsEvent
    data object ToggleShowProfilePictureMenu : SettingsEvent
    data object HideProfilePictureMenu : SettingsEvent
    data class ShowBoardCustomizationDialog(val value: Boolean) : SettingsEvent
    data class SetBoardShape(val shape: BoardShape, val index: Int, val isRGBEffectOn: Boolean, val isGradientOn: Boolean) : SettingsEvent
    data class UpdateBoardCardsBorderColor(val color: Color) : SettingsEvent
}

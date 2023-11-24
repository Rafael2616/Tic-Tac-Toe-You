package com.rafael.tictactoe.settings.domain.use_cases

import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.domain.enums.SettingsGroup
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.domain.model.SettingsState

fun toggleSettingsUseCase(
    groupType: SettingsGroup,
	index: Int,
	state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {

    fun toggleThemeSettings(index: Int) {
        when (index) {
            0 -> onEvent(SettingsEvent.ToggleIsFollowingSystemOn)
            1 -> onEvent(SettingsEvent.ToggleIsDarkThemeOn)
            2 -> onEvent(SettingsEvent.ToggleIsDynamicColorsOn)
        }
    }

    fun toggleSoundSettings(index: Int) {
        when (index) {
            0 -> onEvent(SettingsEvent.ToggleIsSoundEffectsOn)
            1 -> onEvent(SettingsEvent.ToggleIsBackgroundMusicOn)
        }
    }

    fun toggleCustomizationSettings(index: Int) {
        onEvent(SettingsEvent.ShowColorSelectionDialog(true))
        when (index) {
            0 -> onEvent(SettingsEvent.SetCustomizationType(Customization.WINNERLINECOLOR))
            1 -> onEvent(SettingsEvent.SetCustomizationType(Customization.CIRCLECOLOR))
            2 -> onEvent(SettingsEvent.SetCustomizationType(Customization.XCOLOR))
        }
    }

    fun toggleOtherSettings(index: Int) {
        when (index) {
            0 -> onEvent(SettingsEvent.ShowAboutAppCard(!state.isAboutShown))
        }   
    }

    when (groupType) {
        SettingsGroup.Theme -> toggleThemeSettings(index)
        SettingsGroup.Sound -> toggleSoundSettings(index)
        SettingsGroup.Customization -> toggleCustomizationSettings(index)
        SettingsGroup.Others -> toggleOtherSettings(index)
    }
}
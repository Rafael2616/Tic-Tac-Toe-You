package com.rafael.tictactoe.settings.domain.use_cases

import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.domain.model.SettingsState

fun toggleSettingsUseCase(
    groupType: GroupType,
	index: Int,
	state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {

    fun toggleThemeSettings(index: Int) {
        when (index) {
            0 -> onEvent(SettingsEvent.toggleIsFollowingSystemOn)
            1 -> onEvent(SettingsEvent.toggleIsDarkThemeOn(!state.isDarkThemeOn))
            2 -> onEvent(SettingsEvent.toggleIsDynamicColorsOn)
        }
    }

    fun toggleSoundSettings(index: Int) {
        when (index) {
            0 -> onEvent(SettingsEvent.toggleIsSoundEffectsOn)
            1 -> onEvent(SettingsEvent.toggleIsBackgroundMusicOn)
        }
    }

    fun toggleCustomizationSettings(index: Int) {
        onEvent(SettingsEvent.showColorSelectionDialog(true))
        when (index) {
            0 -> onEvent(SettingsEvent.setCustomizationType(Customization.WINNERLINECOLOR))
            1 -> onEvent(SettingsEvent.setCustomizationType(Customization.CIRCLECOLOR))
            2 -> onEvent(SettingsEvent.setCustomizationType(Customization.XCOLOR))
        }
    }

    fun toggleOtherSettings(index: Int) {
        when (index) {
            0 -> onEvent(SettingsEvent.updateIsAboutShown(!state.isAboutShown))
        }   
    }

    when (groupType) {
        GroupType.THEME -> toggleThemeSettings(index)
        GroupType.SOUND -> toggleSoundSettings(index)
        GroupType.CUSTOMIZATION -> toggleCustomizationSettings(index)
        GroupType.OTHER -> toggleOtherSettings(index)
    }
}
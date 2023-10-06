package com.rafael.tictactoe.settings.domain.use_cases

import com.rafael.tictactoe.settings.domain.util.Customization
import com.rafael.tictactoe.settings.domain.util.GroupType
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun toggleSettingsUseCase(
    groupType: GroupType,
	index: Int,
	vm: SettingsViewModel
) {

    fun toggleThemeSettings(index: Int) {
        when (index) {
            0 -> vm.updateIsFollowingSystemOn(!vm.isFollowingSystemOn.value)
            1 -> vm.updateIsDarkThemeOn(!vm.isDarkThemeOn.value)
            2 -> vm.updateIsDynamicColorsOn(!vm.isDynamicColorsOn.value)
        }
    }

    fun toggleSoundSettings(index: Int) {
        when (index) {
            0 -> vm.updateIsSoundEffectsOn(!vm.isSoundEffectsOn.value)
            1 -> vm.updateIsBackgroundMusicOn(!vm.isBackgroundMusicOn.value)
        }
    }

    fun toggleCustomizationSettings(index: Int) {
        when (index) {
            0 -> vm.showLineColorSelectorDialog(true, Customization.WINNERLINECOLOR)
            1 -> vm.showLineColorSelectorDialog(true, Customization.COLORCIRCLE)
            2 -> vm.showLineColorSelectorDialog(true, Customization.COLORX)
        }   
    }

    fun toggleOtherSettings(index: Int) {
        when (index) {
            0 -> vm.updateIsAboutShown(!vm.isAboutShown.value)
        }   
    }

    when (groupType) {
        GroupType.THEME -> toggleThemeSettings(index)
        GroupType.SOUND -> toggleSoundSettings(index)
        GroupType.CUSTOMIZATION -> toggleCustomizationSettings(index)
        GroupType.OTHER -> toggleOtherSettings(index)
    }
}
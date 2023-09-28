package com.rafael.tictactoe.settings.domain.use_cases

import android.content.Context
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun toggleSettingsUseCase(
	context: Context,
	index: Int,
	vm: SettingsViewModel
) {
    when (index) {
        0 -> vm.updateIsFollowingSystemOn(!vm.isFollowingSystemOn.value)
        1 -> vm.updateIsDarkThemeOn(!vm.isDarkThemeOn.value)
        2 -> vm.updateIsDynamicColorsOn(!vm.isDynamicColorsOn.value)
        3 -> vm.updateIsSoundEffectsOn(!vm.isSoundEffectsOn.value)
        4 -> vm.updateIsBackgroundMusicOn(!vm.isBackgroundMusicOn.value, context)
        5 -> vm.showWinnerLineColorDialog(true)
        6 -> vm.updateIsAboutShown(!vm.isAboutShown.value)
    }
}
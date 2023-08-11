package com.rafael.tictactoe.settings.domain.util

import android.content.Context
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.settings.domain.util.SyncDarkThemeToggleValue
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun SwitchOnCheckedChangeHandler(
	context: Context,
	index: Int,
	vm: SettingsViewModel,
	isSystemInDarkTheme: Boolean
) {
    when (index){
        0 -> { vm.updateIsFollowingSystemOn(!vm.isFollowingSystemOn.value); SyncDarkThemeToggleValue(vm, isSystemInDarkTheme)}
        1 -> vm.updateIsDarkThemeOn(!vm.isDarkThemeOn.value)
        2 -> vm.updateIsDynamicColorsOn(!vm.isDynamicColorsOn.value)
        3 -> vm.updateIsSoundEffectsOn(!vm.isSoundEffectsOn.value)
        4 -> vm.updateIsBackgroundMusicOn(!vm.isBackgroundMusicOn.value, context)
        5 -> vm.updateIsAboutShown(!vm.isAboutShown.value)
    }
}
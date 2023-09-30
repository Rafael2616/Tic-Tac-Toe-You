package com.rafael.tictactoe.settings.domain.util

import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun switchCheckedHandler(
    groupType: GroupType,
    index: Int,
    viewModel: SettingsViewModel
): Boolean {
   
    fun themeSwitchChecked(index: Int, vm: SettingsViewModel): Boolean {
        return when (index) {
    		0 -> vm.isFollowingSystemOn.value
 		   1 -> vm.isDarkThemeOn.value
   	     2 -> vm.isDynamicColorsOn.value
        	else -> false 
        } 
    }
   
    fun audioSwitchChecked(index: Int, vm: SettingsViewModel): Boolean {
		return when (index) {
    		0 -> vm.isSoundEffectsOn.value
     	   1 -> vm.isBackgroundMusicOn.value
            else -> false
        }
    }

    return when (groupType) {
        GroupType.THEME -> themeSwitchChecked(index, viewModel)
        GroupType.SOUND -> audioSwitchChecked(index, viewModel)
        else -> false
    }  
}
package com.rafael.tictactoe.settings.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun switchCheckedHandler(
    groupType: GroupType,
    index: Int
): Boolean {
    val vm = hiltViewModel<SettingsViewModel>()

    @Composable
    fun themeSwitchCheckedHandler(index: Int): Boolean {
   	 val isFollowingSystemOn by vm.isFollowingSystemOn.collectAsState()
 	   val isDarkThemeOn by vm.isDarkThemeOn.collectAsState()
   	 val isDynamicColorsOn by vm.isDynamicColorsOn.collectAsState()

        return when (index) {
   		 0 -> isFollowingSystemOn
 		   1 -> isDarkThemeOn
   	     2 -> isDynamicColorsOn
        	else -> false
        } 
    }

    @Composable
    fun audioSwitchCheckedHandler(index: Int): Boolean {
    	val isSoundEffectsOn by vm.isSoundEffectsOn.collectAsState()
        val isBackgroundMusicOn by vm.isBackgroundMusicOn.collectAsState()

		return when (index) {
    		0 -> isSoundEffectsOn
     	   1 -> isBackgroundMusicOn
            else -> false
        }
    }

    return when (groupType) {
        GroupType.THEME -> themeSwitchCheckedHandler(index)
        GroupType.SOUND -> audioSwitchCheckedHandler(index)
        else -> false
    }  
}
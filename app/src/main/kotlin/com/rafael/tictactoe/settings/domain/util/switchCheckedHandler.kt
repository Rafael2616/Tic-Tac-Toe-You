package com.rafael.tictactoe.settings.domain.util

import androidx.compose.runtime.Composable
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun switchCheckedHandler(
    groupType: GroupType,
    index: Int,
    state: SettingsState
): Boolean {

    @Composable
    fun themeSwitchCheckedHandler(index: Int): Boolean {
   	 val isFollowingSystemOn = state.isFollowingSystemOn
 	   val isDarkThemeOn = state.isDarkThemeOn
   	 val isDynamicColorsOn = state.isDynamicColorsOn

        return when (index) {
   		 0 -> isFollowingSystemOn
 		   1 -> isDarkThemeOn
   	     2 -> isDynamicColorsOn
        	else -> false
        } 
    }

    @Composable
    fun audioSwitchCheckedHandler(index: Int): Boolean {
    	val isSoundEffectsOn = state.isSoundEffectsOn
        val isBackgroundMusicOn = state.isBackgroundMusicOn

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
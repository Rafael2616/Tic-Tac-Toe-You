package com.rafael.tictactoe.settings.domain.util

fun SwitchValuesHandler(
index: Int,
isFollowingSystemOn: Boolean,
isDarkThemeOn: Boolean,
isDynamicColorsOn: Boolean,
isSoundEffectsOn: Boolean,
isBackgroundMusicOn: Boolean,
isAboutShown: Boolean
): Boolean {

	return when (index) {
        0 -> isFollowingSystemOn
        1 -> isDarkThemeOn
        2 -> isDynamicColorsOn
        3 -> isSoundEffectsOn
        4 -> isBackgroundMusicOn
        5 -> isAboutShown
     else -> false
    }
}
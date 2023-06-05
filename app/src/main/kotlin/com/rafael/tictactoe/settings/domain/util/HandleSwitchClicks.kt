package com.rafael.tictactoe.settings.domain.util

import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import android.content.*
import com.rafael.tictactoe.settings.presentation.settings_screen.activities.SettingsActivity


fun HandleSwitchClicks(
index: Int,
viewModel: SettingsViewModel,
isFollowingSystemOn: Boolean,
isDarkThemeOn: Boolean,
isDynamicColorsOn: Boolean,
isSoundEffectsOn: Boolean,
isBackgroundMusicOn: Boolean,
isAboutShown: Boolean
) {
when (index){
    0 -> viewModel.updateIsFollowingSystemOnValue(!isFollowingSystemOn)
    1 -> viewModel.updateIsDarkThemeOnValue(!isDarkThemeOn)
    2 -> viewModel.updateIsDynamicColorsOnValue(!isDynamicColorsOn)
    3 -> viewModel.updateIsSoundEffectsOnValue(!isSoundEffectsOn)
    4 -> viewModel.updateIsBackgroundMusicOnValue(!isBackgroundMusicOn)
    5 -> viewModel.updateIsAboutShownValue(!isAboutShown)
    }
}
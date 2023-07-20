package com.rafael.tictactoe.settings.domain.util

import android.content.Context
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun HandleSwitchClicks(
context: Context,
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
    0 -> { viewModel.updateIsFollowingSystemOn(!isFollowingSystemOn); viewModel.updateIsContentSetted(false) }
    1 -> { viewModel.updateIsDarkThemeOn(!isDarkThemeOn); viewModel.updateIsContentSetted(false) }
    2 -> { viewModel.updateIsDynamicColorsOn(!isDynamicColorsOn); viewModel.updateIsContentSetted(false) }
    3 -> viewModel.updateIsSoundEffectsOn(!isSoundEffectsOn)
    4 -> viewModel.updateIsBackgroundMusicOn(!isBackgroundMusicOn, context)
    5 -> viewModel.updateIsAboutShown(!isAboutShown)
    }
}
package com.rafael.tictactoe.settings.domain.model

import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.enums.Customization

data class SettingsState(
    val color : Colors = Colors.get(),
    val isBackgroundMusicOn: Boolean = false,
    val isDarkThemeOn: Boolean = true,
    val isDynamicColorsOn: Boolean = true,
    val isFollowingSystemOn: Boolean = true,
    val isSoundEffectsOn: Boolean = true,
    val isAboutShown: Boolean = false,
    val showColorSelectionDialog: Boolean = false,
    val winnerLineColor: Color = color.yellow,
    val selectedWinnerLineColor: Int = 2,
    val xColor: Color = color.red,
    val selectedXColor: Int = 1,
    val circleColor: Color = color.light_green,
    val selectedCircleColor: Int = 5,
    val isBMPlaying: Boolean = false,
    val customizationType: Customization = Customization.XCOLOR
)
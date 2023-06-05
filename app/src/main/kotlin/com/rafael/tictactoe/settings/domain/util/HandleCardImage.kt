package com.rafael.tictactoe.settings.domain.util

import com.rafael.tictactoe.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.res.*


@Composable
fun HandleCardImage(
index: Int,
isDarkThemeOn: Boolean,
isDynamicColorsOn: Boolean,
isSoundEffectsOn: Boolean,
isBackgroundMusicOn: Boolean
): Painter {
    return when (index) {
        0 -> painterResource(R.drawable.system_theme_icon)
        1 -> if (isDarkThemeOn) painterResource(R.drawable.dark_theme_icon) else painterResource(R.drawable.light_theme_icon)
        2 -> if (isDynamicColorsOn) painterResource(R.drawable.dynamic_colors_on_icon) else painterResource(R.drawable.dynamic_colors_off_icon)
        3 -> if (isSoundEffectsOn) painterResource(R.drawable.sound_effects_on_icon) else painterResource(R.drawable.sound_effects_off_icon)
        4 -> if (isBackgroundMusicOn) painterResource(R.drawable.sound_on_icon) else painterResource(R.drawable.sound_off_icon)
        5 -> painterResource(R.drawable.about_icon)
        else -> painterResource(R.drawable.transparent_image)
    }
}
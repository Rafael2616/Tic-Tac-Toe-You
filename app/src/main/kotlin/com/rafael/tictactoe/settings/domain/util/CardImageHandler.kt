package com.rafael.tictactoe.settings.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun CardImageHandler(index: Int): Painter {
    val viewModel = viewModel<SettingsViewModel>()
    val isDarkThemeOn by viewModel.isDarkThemeOn.collectAsState()
    val isDynamicColorsOn by viewModel.isDynamicColorsOn.collectAsState()
    val isBackgroundMusicOn by viewModel.isBackgroundMusicOn.collectAsState()
    val isSoundEffectsOn by viewModel.isSoundEffectsOn.collectAsState()

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
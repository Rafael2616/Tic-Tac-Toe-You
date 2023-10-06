package com.rafael.tictactoe.settings.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SettingsImageHandler(index: Int, groupType: GroupType): Painter {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val isDarkThemeOn by viewModel.isDarkThemeOn.collectAsState()
    val isDynamicColorsOn by viewModel.isDynamicColorsOn.collectAsState()
    val isBackgroundMusicOn by viewModel.isBackgroundMusicOn.collectAsState()
    val isSoundEffectsOn by viewModel.isSoundEffectsOn.collectAsState()
    
    @Composable
    fun ThemeCardImageHandler(index: Int): Painter {
        return when (index) {
            0 -> painterResource(R.drawable.system_theme_icon)
            1 -> if (isDarkThemeOn) painterResource(R.drawable.dark_theme_icon) else painterResource(R.drawable.light_theme_icon)
            2 -> if (isDynamicColorsOn) painterResource(R.drawable.dynamic_colors_on_icon) else painterResource(R.drawable.dynamic_colors_off_icon)
           else -> painterResource(R.drawable.transparent_image)
        } 
    }
    
    @Composable
    fun SoundCardImageHandler(index: Int): Painter {
        return when (index) {
            0 -> if (isSoundEffectsOn) painterResource(R.drawable.sound_effects_on_icon) else painterResource(R.drawable.sound_effects_off_icon)
            1 -> if (isBackgroundMusicOn) painterResource(R.drawable.sound_on_icon) else painterResource(R.drawable.sound_off_icon)
            else -> painterResource(R.drawable.transparent_image)
        } 
    }

    return when (groupType) {
        GroupType.THEME -> ThemeCardImageHandler(index)
        GroupType.SOUND -> SoundCardImageHandler(index)
        else -> painterResource(R.drawable.transparent_image)
    }
}
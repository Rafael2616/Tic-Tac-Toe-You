package com.rafael.tictactoe.settings.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SettingsTextHandler(index: Int, groupType: GroupType): String {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val isAboutShown by settingsVM.isAboutShown.collectAsState()

   @Composable
    fun themeCardTextHandler(index: Int): String {
        return when (index) {
            0 -> stringResource(R.string.system_theme)
            1 -> stringResource(R.string.dark_theme)
            2 -> stringResource(R.string.material_you)
           else -> stringResource(R.string.index_error)
        } 
    }
    
    @Composable
    fun soundCardTextHandler(index: Int): String {
        return when (index) {
            0 -> stringResource(R.string.sound_effects)
            1 -> stringResource(R.string.background_music)
           else -> stringResource(R.string.index_error)
        } 
    }

    @Composable
    fun customizationCardTextHandler(index: Int): String {
        return when (index) {
            0 -> stringResource(R.string.winner_line_color)
            1 -> stringResource(R.string.color_circle)
            2 -> stringResource(R.string.color_x)
            else -> stringResource(R.string.index_error)
        }
    }

    @Composable
    fun otherCardTextHandler(index: Int): String {
        return when (index) {
            0 -> stringResource(id = if (!isAboutShown) R.string.about_app else R.string.creator_message)
            else -> stringResource(R.string.index_error)
        }
    }

    return when (groupType) {
        GroupType.THEME -> themeCardTextHandler(index)
        GroupType.SOUND -> soundCardTextHandler(index)
        GroupType.CUSTOMIZATION -> customizationCardTextHandler(index)
        GroupType.OTHER -> otherCardTextHandler(index)
    }
}
package com.rafael.tictactoe.settings.domain.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessMedium
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun SettingsFirstIconHandler(
    index: Int,
    groupType: GroupType,
    state: SettingsState
): ImageVector {
    val isSoundEffectsOn = state.isSoundEffectsOn
    val isBackgroundMusicOn = state.isBackgroundMusicOn
    val isDarkThemeOn = state.isDarkThemeOn

    @Composable
    fun ThemeIconHandler(index: Int): ImageVector {
        return when (index) {
            0 -> Icons.Filled.BrightnessMedium
            1 -> if (isDarkThemeOn) Icons.Filled.DarkMode else Icons.Filled.LightMode
            2 -> Icons.Filled.Palette
            else -> Icons.Filled.Close
        }
    }

    @Composable
    fun SoundIconHandler(index: Int): ImageVector {
        return when (index) {
            0 -> if (isSoundEffectsOn) Icons.Filled.MusicNote else Icons.Filled.MusicOff
            1 -> if (isBackgroundMusicOn) Icons.Filled.VolumeUp else Icons.Filled.VolumeOff
            else -> Icons.Filled.Close
        }
    }

    @Composable
    fun OtherIconHandler(index: Int): ImageVector {
        return when (index) {
            0 -> Icons.Outlined.Info
            else -> Icons.Filled.Close
        }
    }

    return when (groupType) {
        GroupType.THEME -> ThemeIconHandler(index)
        GroupType.SOUND -> SoundIconHandler(index)
        GroupType.OTHER -> OtherIconHandler(index)
        else -> Icons.Filled.Close
    }
}
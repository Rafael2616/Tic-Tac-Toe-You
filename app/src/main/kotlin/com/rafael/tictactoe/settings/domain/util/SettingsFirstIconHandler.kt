package com.rafael.tictactoe.settings.domain.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.automirrored.outlined.VolumeOff
import androidx.compose.material.icons.outlined.BrightnessMedium
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.MusicOff
import androidx.compose.material.icons.outlined.Palette
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

    @Composable
    fun ThemeIconHandler(index: Int): ImageVector {
        return when (index) {
            0 -> Icons.Outlined.BrightnessMedium
            1 -> if (state.isDarkThemeOn) Icons.Outlined.DarkMode else Icons.Outlined.LightMode
            2 -> Icons.Outlined.Palette
            else -> Icons.Outlined.Close
        }
    }

    @Composable
    fun SoundIconHandler(index: Int): ImageVector {
        return when (index) {
            0 -> if (state.isSoundEffectsOn) Icons.Outlined.MusicNote else Icons.Outlined.MusicOff
            1 -> if (state.isBackgroundMusicOn) Icons.AutoMirrored.Outlined.VolumeUp else Icons.AutoMirrored.Outlined.VolumeOff
            else -> Icons.Outlined.Close
        }
    }

    @Composable
    fun OtherIconHandler(index: Int): ImageVector {
        return when (index) {
            0 -> Icons.Outlined.Info
            else -> Icons.Outlined.Close
        }
    }

    return when (groupType) {
        GroupType.THEME -> ThemeIconHandler(index)
        GroupType.SOUND -> SoundIconHandler(index)
        GroupType.OTHER -> OtherIconHandler(index)
        else -> Icons.Outlined.Close
    }
}
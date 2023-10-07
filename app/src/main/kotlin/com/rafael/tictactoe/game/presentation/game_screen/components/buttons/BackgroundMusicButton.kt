package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton

@Composable
fun BackgroundMusicButton() {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val isBackgroundMusicOn by settingsVM.isBackgroundMusicOn.collectAsState()
 
    BaseButton(
        modifier = Modifier.padding(top = 165.dp, start = 15.dp),
        btnIcon = if (isBackgroundMusicOn) Icons.Filled.VolumeUp else Icons.Filled.VolumeOff,
        btnText = R.string.active_music,
        tint = if (isBackgroundMusicOn) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.error,
        description = "Background Music Button",
        onClickFun = { 
            settingsVM.updateIsBackgroundMusicOn(!isBackgroundMusicOn)
        }
    )
}
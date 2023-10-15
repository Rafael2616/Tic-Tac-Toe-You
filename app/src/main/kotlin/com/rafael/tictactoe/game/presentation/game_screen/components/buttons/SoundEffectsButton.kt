package com.rafael.tictactoe.game.presentation.game_screen.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton

@Composable
fun SoundEffectsButton() {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()

    BaseButton(
        modifier = Modifier.padding(top = 95.dp, start = 15.dp),
        btnIcon = if (isSoundEffectsOn) Icons.Filled.MusicNote else Icons.Filled.MusicOff,
        btnText = R.string.active_sound,
        description = "Sound Effects Button",
        onClickFun = { 
            settingsVM.updateIsSoundEffectsOn(!isSoundEffectsOn)
        }
    )
}
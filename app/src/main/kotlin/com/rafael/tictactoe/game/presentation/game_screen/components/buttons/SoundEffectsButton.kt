package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton

@Composable
fun SoundEffectsButton() {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()

    BaseButton(
        topPadding = 95.dp,
        startPadding = 15.dp,
        btnImage = if (isSoundEffectsOn) R.drawable.sound_effects_on_icon
        else R.drawable.sound_effects_off_icon,
        btnText = R.string.active_sound,
        description = "Sound Effects Button",
        onClickFun = { 
            settingsVM.updateIsSoundEffectsOn(!isSoundEffectsOn)
        }
    )
}
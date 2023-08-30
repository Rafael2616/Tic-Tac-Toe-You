package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton

@Composable
fun BackgroundMusicButton() {
    val settingsVM = viewModel<SettingsViewModel>()
    val context = LocalContext.current
    val isBackgroundMusicOn by settingsVM.isBackgroundMusicOn.collectAsState()
 
    BaseButton(
        topPadding = 165.dp,
        startPadding = 15.dp,
        btnImage = if (isBackgroundMusicOn) R.drawable.sound_on_icon
        else  R.drawable.sound_off_icon,
        btnText = R.string.active_music,
        description = "Background Music Button",
        onClickFun = { 
            settingsVM.updateIsBackgroundMusicOn(!isBackgroundMusicOn, context)
        }
    )
}
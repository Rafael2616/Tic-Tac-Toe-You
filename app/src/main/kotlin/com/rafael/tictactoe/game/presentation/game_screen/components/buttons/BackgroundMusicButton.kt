package com.rafael.tictactoe.game.presentation.game_screen.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun BackgroundMusicButton(
    onEvent: (GameEvent) -> Unit,
    settingsState: SettingsState
) { 
    BaseButton(
        modifier = Modifier.padding(top = 165.dp, start = 15.dp),
        btnIcon = if (settingsState.isBackgroundMusicOn) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
        btnText = R.string.active_music,
        description = "Background Music Button",
        onEvent = { 
            onEvent(GameEvent.ToggleBgMusicButton)
        }
    )
}
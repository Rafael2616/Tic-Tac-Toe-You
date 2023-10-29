package com.rafael.tictactoe.game.presentation.game_screen.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.MusicOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun SoundEffectsButton(
    onEvent: (GameEvent) -> Unit,
    settingsState: SettingsState
) {
    BaseButton(
        modifier = Modifier.padding(top = 95.dp, start = 15.dp),
        btnIcon = if (settingsState.isSoundEffectsOn) Icons.Outlined.MusicNote else Icons.Outlined.MusicOff,
        btnText = R.string.active_sound,
        description = "Sound Effects Button",
        onEvent = { 
            onEvent(GameEvent.ToggleSoundEffectsButton)
        }
    )
}
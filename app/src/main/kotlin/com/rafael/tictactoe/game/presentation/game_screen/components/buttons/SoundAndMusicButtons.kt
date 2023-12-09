package com.rafael.tictactoe.game.presentation.gameScreen.components.buttons

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.VolumeOff
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.MusicOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun SoundAndMusicButtons(
    onEvent: (GameEvent) -> Unit,
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 55.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        Column(
            modifier = Modifier.animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // Expand Button, when clicked shows sound effects and background music buttons
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .clickable {
                        onEvent(GameEvent.ToggleExpandButton)
                    },
            ) {
                Icon(
                    imageVector = if (state.isExpandButtonShown) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = "Expand Button",
                    modifier = Modifier.size(42.dp),
                )
            }
            if (state.isExpandButtonShown) {
                Spacer(Modifier.height(3.dp))
                Buttons.values().forEachIndexed { _, button ->
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp),
                        ),
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .clickable {
                                when (button) {
                                    Buttons.SOUND_EFFECTS -> onEvent(GameEvent.ToggleSoundEffectsButton)
                                    Buttons.BG_MUSIC -> onEvent(GameEvent.ToggleBgMusicButton)
                                }
                            },
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Icon(
                                imageVector = when (button) {
                                    Buttons.SOUND_EFFECTS -> if (settingsState.isSoundEffectsOn) Icons.Outlined.MusicNote else Icons.Outlined.MusicOff
                                    Buttons.BG_MUSIC -> if (settingsState.isBackgroundMusicOn) Icons.AutoMirrored.Outlined.VolumeUp else Icons.AutoMirrored.Outlined.VolumeOff
                                },
                                contentDescription = when (button) {
                                    Buttons.SOUND_EFFECTS -> "Sound Effects Button"
                                    Buttons.BG_MUSIC -> "Background Music Button"
                                },
                                modifier = Modifier.size(38.dp),
                            )
                        }
                    }
                    Text(
                        text = stringResource(
                            when (button) {
                                Buttons.SOUND_EFFECTS -> R.string.active_sound
                                Buttons.BG_MUSIC -> R.string.active_music
                            },
                        ),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 4.dp, bottom = 5.dp),
                    )
                }
            }
        }
    }
}

enum class Buttons {
    SOUND_EFFECTS,
    BG_MUSIC,
}

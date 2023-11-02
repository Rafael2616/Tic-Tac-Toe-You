package com.rafael.tictactoe.game.presentation.game_screen.components.buttons

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.animation.animateContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.automirrored.outlined.VolumeOff
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.MusicOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
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
    settingsState: SettingsState
) {
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)
    val buttons = remember { mutableStateListOf(0,1) }

    Box(
        modifier = Modifier
            .fillMaxWidth(if (isInLandscape) 0.48f else 1f)
            .padding(start = 15.dp, top = 55.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //Expand Button, when clicked shows sound effects and background music buttons
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .clickable {
                        onEvent(GameEvent.ToggleExpandButton)
                    }
            ) {
                Icon(
                    imageVector = if (state.isExpandButtonShown) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = "Expand Button",
               	 modifier = Modifier.size(42.dp)
                )
            }
            if (state.isExpandButtonShown) {
                Spacer(modifier = Modifier.height(3.dp))
                buttons.forEach { button ->
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                        ),
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .clickable {
                                when (button) {
                                    0 -> onEvent(GameEvent.ToggleSoundEffectsButton)
                                    else -> onEvent(GameEvent.ToggleBgMusicButton)
                                }
                            }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = when (button) {
                                    0 -> if (settingsState.isSoundEffectsOn) Icons.Outlined.MusicNote else Icons.Outlined.MusicOff
                                    else -> if (settingsState.isBackgroundMusicOn) Icons.AutoMirrored.Outlined.VolumeUp else Icons.AutoMirrored.Outlined.VolumeOff
                                },
                                contentDescription = when (button) {
                                    0 -> "Sound Effects Button"
                                    else ->  "Background Music Button"
                                },
                                modifier = Modifier.size(38.dp)
                            )
                        }
                    }
                    Text(
                        text = stringResource(when (button) {
                            0 -> R.string.active_sound
                            else -> R.string.active_music
                        }),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 4.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}
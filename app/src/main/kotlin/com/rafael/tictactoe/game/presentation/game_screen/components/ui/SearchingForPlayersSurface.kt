package com.rafael.tictactoe.game.presentation.game_screen.components.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Replay
import androidx.compose.material.icons.outlined.SentimentDissatisfied
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.Error
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.model.RequestState
import com.rafael.tictactoe.settings.domain.model.SettingsState
import kotlinx.coroutines.delay

@Composable
fun SearchingForPlayersSurface(
    gameState: GameState,
    settingsState: SettingsState,
    onEvent: (GameEvent) -> Unit
) {
    val requestState = gameState.requestState

    Surface(
        color = if (settingsState.isDarkThemeOn) {
            Color.Black.copy(alpha = 0.5f)
        } else MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp).copy(alpha = 0.5f),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    modifier = Modifier
                       .padding(end = 15.dp)
                       .size(45.dp),
                    onClick = {
                        onEvent(GameEvent.UpdateRequestState(RequestState.Loading))
                        onEvent(GameEvent.GetRemotePlayers)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Replay,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                }
                IconButton(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .size(45.dp),
                    onClick = {
                        onEvent(GameEvent.ToggleExitDialog)
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Logout,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
            Text(
                 modifier = Modifier
                     .fillMaxWidth(0.8f)
                     .weight(1.4f),
                 text = stringResource( id = when (requestState) {
                     RequestState.Loading -> R.string.searching_for_players
                     RequestState.Success -> R.string.player_found
                     is RequestState.Failure -> {
                         when (requestState.type) {
                             Error.IOError -> {
                                 if (requestState.message.contains("404") && !requestState.message.contains("No players")) {
                                      R.string.unable_connect_server
                                  } else if (requestState.message.contains("UnknownHostException")) {
                                      R.string.connection_error
                                  } else if (requestState.message.contains("No players")) {
                                      R.string.cannot_find_player
                                 } else R.string.unknown_error
                             }
                             Error.HttpError -> R.string.problem_during_server_connection
                         }
                     }
                 }),
                 style = MaterialTheme.typography.titleMedium.copy(
                     fontSize = 25.sp,
                     fontWeight = FontWeight.Bold
                 ),
                 color = MaterialTheme.colorScheme.onSurface,
                 textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .weight(0.35f),
                text = if (requestState == RequestState.Success) {
                     stringResource(id = R.string.player_n) + " " + "<Nome do Jogador>"// + state.secondPlayer
                 } else "",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var timer by rememberSaveable { mutableIntStateOf(4) }
            LaunchedEffect(Unit) {
                if (requestState == RequestState.Success) {
                    while(timer >= 0) {
                        timer --
                        delay(900L)
                        if (timer == 0) {
                            onEvent(GameEvent.StartOnlineMatch)
                        }
                    }
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .weight(0.35f),
                text = if (requestState == RequestState.Success) {
                    stringResource(id = R.string.game_will_start) + " " + timer.toString()
                } else "",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = Color.Green
            )
            Box(
                modifier = Modifier
                    .weight(3.3f)
                    .fillMaxWidth(),
                 contentAlignment = Alignment.Center
            ) {
                when (requestState) {
                    RequestState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(70.dp),
                            strokeCap = StrokeCap.Round,
                            strokeWidth = 6.dp
                        )
                    }
                    is RequestState.Failure -> {
                        Icon(
                            imageVector = Icons.Outlined.SentimentDissatisfied,
                            contentDescription = null,
                            modifier = Modifier
                                .size(70.dp),
                            tint = Color.Red
                        )
                    }
                    RequestState.Success -> {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = null,
                            modifier = Modifier
                                .size(70.dp),
                            tint = Color.Green
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth(0.95f),
                text = ">>>  " + stringResource( id = if (gameState.isYouPlayer1) {
                    R.string.you_will_start_game
                } else {
                    R.string.you_will_be_second_player
                }) + "  <<<",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(0.9f),
                text = when (requestState) {
                    is RequestState.Failure -> {
                        when (requestState.type) {
                            Error.HttpError -> "Failure: ${requestState.message}\nCode: ${requestState.code}"
                            Error.IOError -> "Failure: ${requestState.message}"
                        }
                    }
                    else -> ""
                },
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
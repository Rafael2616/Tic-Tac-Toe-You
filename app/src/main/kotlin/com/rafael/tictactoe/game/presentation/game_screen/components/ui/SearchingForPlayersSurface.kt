package com.rafael.tictactoe.game.presentation.game_screen.components.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.game.domain.model.Error
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.model.RequestState
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun SearchingForPlayersSurface(
    gameState: GameState,
    settingsState: SettingsState,
    onEvent: (GameEvent) -> Unit
) {
    onEvent(GameEvent.GetRemotePlayers)

    Surface(
        color = Color.Black.copy(alpha = if (settingsState.isDarkThemeOn) 0.5f else 0.3f),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
               modifier = Modifier
                    .padding(top = 70.dp)
                    .fillMaxWidth()
                    .height(45.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    modifier = Modifier
                       .padding(end = 20.dp)
                       .size(45.dp),
                    onClick = {
                    onEvent(GameEvent.GetRemotePlayers)
                        onEvent(GameEvent.UpdateRequestState(RequestState.Loading))
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
                        .padding(end = 15.dp)
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
                     .padding(top = 20.dp),
                 text = when (gameState.requestState) {
                 RequestState.Loading -> "Procurando um jogador a sua altura..."
                     is RequestState.Failure -> {
                         when (gameState.requestState.type) {
                             Error.IOError -> "Incapaz de conectar ao servidor, Verifique sua conexão com a internet"
                             Error.NoPlayers -> "Não achamos um jogador digno :("
                             Error.UnableToConnect -> "Não foi possivel conectar ao servidor"
                             Error.HttpError -> "Um problema ocorreu durante a conexão com o servidor remoto"
                         }
                     }
                     RequestState.Success -> "Jogador encontrado: "
                 },
                 style = MaterialTheme.typography.titleMedium.copy(
                     fontSize = 25.sp,
                     fontWeight = FontWeight.Bold
                 ),
                 color = MaterialTheme.colorScheme.onSurface,
                 textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .padding(top = 150.dp)
                    .fillMaxWidth()
                    .height(70.dp),
                 contentAlignment = Alignment.Center
            ) {
                when (gameState.requestState) {
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
                    .padding(top = 110.dp)
                    .fillMaxWidth(0.9f),
                text = if (gameState.isYouPlayer1) {
                    "Você começará o jogo :)"
                } else "Você será o segundo jogador",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(top = 60.dp)
                    .fillMaxWidth(0.9f),
                text = when (gameState.requestState) {
                    is RequestState.Failure -> {
                        when (gameState.requestState.type) {
                            Error.HttpError -> "Failure:\ncode: ${gameState.requestState.code}\nmessage: ${gameState.requestState.message}"
                            else -> "Failure:\nmessage: ${gameState.requestState.message}"
                        }
                    }
                    else -> ""
                },
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}
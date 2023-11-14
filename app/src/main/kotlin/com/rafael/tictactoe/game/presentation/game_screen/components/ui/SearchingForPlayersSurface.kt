package com.rafael.tictactoe.game.presentation.game_screen.components.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.SentimentDissatisfied
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@Composable
fun SearchingForPlayersSurface(
    gameState: GameState,
    onEvent: (GameEvent) -> Unit
) {

   LaunchedEffect(Unit) {
        onEvent(GameEvent.GetRemotePlayers)
    }

    Surface(
        color = Color.Black.copy(alpha = 0.5f),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp),
            contentAlignment = Alignment.TopCenter
        ) {
             Text(
                 modifier = Modifier
                     .fillMaxWidth(0.8f),
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
         }
        Box(
            modifier = Modifier
                .fillMaxSize(),
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 90.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                     modifier = Modifier
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
}
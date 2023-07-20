package com.rafael.tictactoe.game.presentation.game_screen.components

import android.content.Context
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.PlayersNamesDisplay
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun PlayersNamesLayout(
context: Context,
vm: GameViewModel,
playerName: PlayersNamesDisplay,
showPlayer1Name: String,
showPlayer2Name: String,
play: Boolean,
isPlayer1: Boolean,
isXFor1: Boolean,
player1Name: String,
player2Name: String,
player1InToast: Boolean
) {

	playerName.firstPlayerName(context, vm, play, isPlayer1, isXFor1, player1Name, player2Name, player1InToast)
	playerName.secondPlayerName(context, vm, play, isPlayer1, isXFor1, player1Name, player2Name, player1InToast)
	
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = (Alignment.TopCenter)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 24.dp, end = 80.dp)
        ) {
            Text(
                text = ("$showPlayer1Name"),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = (Alignment.TopCenter)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 24.dp, start = 80.dp)
        ) {
            Text(
                text = ("$showPlayer2Name"),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

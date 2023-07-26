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
	viewModel: GameViewModel,
	playerName: PlayersNamesDisplay
) {

    val player1InToast by viewModel.player1InToast.collectAsState()
    val player1Name by viewModel.player1Name.collectAsState()
    val player2Name by viewModel.player2Name.collectAsState()
    val isXFor1 by viewModel.isXFor1.collectAsState()
    val isPlayer1 by viewModel.isPlayer1.collectAsState()
    val play by viewModel.play.collectAsState()
    val showPlayer1Name by viewModel.showPlayer1Name.collectAsState()
    val showPlayer2Name by viewModel.showPlayer2Name.collectAsState()

	playerName.firstPlayerName(context, viewModel, play, isPlayer1, isXFor1, player1Name, player2Name, player1InToast)
	playerName.secondPlayerName(context, viewModel, play, isPlayer1, isXFor1, player1Name, player2Name, player1InToast)
		
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

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
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.util.DisplayPlayerNames
import kotlinx.coroutines.launch


@Composable
fun PlayerNamesLayout(context: Context, repository: GameRepository, playerName: DisplayPlayerNames) {
    var player1Name by remember{ mutableStateOf("") }
    var player2Name by remember{ mutableStateOf("") }

    LaunchedEffect(Unit) {
        player1Name = repository.getShowPlayer1Name()
        player2Name = repository.getShowPlayer2Name()
       // playerName.firstPlayerName(context)
       // playerName.secondPlayerName(context)
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
                .padding(top = 24.dp, end = 80.dp)
        ) {
            Text(
                text = ("$player2Name"),
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
                text = ("$player2Name"),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

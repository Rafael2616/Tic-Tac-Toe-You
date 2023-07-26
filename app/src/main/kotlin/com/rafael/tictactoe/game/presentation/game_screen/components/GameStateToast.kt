package com.rafael.tictactoe.game.presentation.game_screen.components

import android.content.Context
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.ToastNameLogic
import com.rafael.tictactoe.game.domain.util.ToastImageLogic
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun GameStateToast(
	context: Context,
	viewModel: GameViewModel
) {

    val currentPlayerName by viewModel.currentPlayerName.collectAsState()
    val player1Name by viewModel.player1Name.collectAsState()
    val player2Name by viewModel.player2Name.collectAsState()
    val isXImage by viewModel.isXImage.collectAsState()
    val isXFor1 by viewModel.isXFor1.collectAsState()
    val isPlayer1 by viewModel.isPlayer1.collectAsState()
    val winner by viewModel.winner.collectAsState()
    val gotOld by viewModel.gotOld.collectAsState()

    ToastNameLogic(context, viewModel, winner, isPlayer1, gotOld, player1Name, player2Name)

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 140.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$currentPlayerName",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (!gotOld) MaterialTheme.colorScheme.onSurface
                        else MaterialTheme.colorScheme.error,
                    )
                    if (!gotOld) {
                        Spacer(modifier = Modifier.padding(3.dp))
                        Image(
                            contentDescription = null,
                            painter = ToastImageLogic(viewModel),
                            modifier = Modifier.height(17.dp).width(17.dp)
                        )
                    }
                    if (winner) {
                        Spacer(modifier = Modifier.padding(3.dp))
                        Text(
                            text = stringResource(R.string.winner),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Green
                        )
                    }
                }
            }
        }
    }
}
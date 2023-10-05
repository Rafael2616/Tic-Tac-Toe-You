package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.utils.draws.drawOinCounter
import com.rafael.tictactoe.game.presentation.utils.draws.drawXinCounter
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun VictoryCounter() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val player1Wins by viewModel.player1Wins.collectAsState()
    val player2Wins by viewModel.player2Wins.collectAsState()
    val isXFor1 by viewModel.isXFor1.collectAsState()
    val colorX by settingsVM.colorX.collectAsState()
    val colorO by settingsVM.colorO.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = (Alignment.TopCenter)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 55.dp)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .width(65.dp)
                        .height(55.dp)
                ) {
                    Text(
                        text = "$player1Wins",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    ) {
                        if (isXFor1) {
                            drawXinCounter(colorX)
                        } else {
                            drawOinCounter(colorO)
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .padding(6.dp)
                )
                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .width(65.dp)
                        .height(55.dp)
                ) {
                    Text(
                        text = "$player2Wins",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    Canvas(
                        modifier = Modifier
                            .width(65.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        if (isXFor1) {
                            drawOinCounter(colorO)
                         } else {
                            drawXinCounter(colorX)
                        }
                    }
                }
            }
        }
    }
}
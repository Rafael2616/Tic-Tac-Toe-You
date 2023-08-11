package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun VictoryCounter() {
    val viewModel = viewModel<GameViewModel>()
    val player1Wins by viewModel.player1Wins.collectAsState()
    val player2Wins by viewModel.player2Wins.collectAsState()

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
                    Image(
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.x_image)
                    )
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
                    Image(
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.circle_image)
                    )
                }
            }
        }
    }
}
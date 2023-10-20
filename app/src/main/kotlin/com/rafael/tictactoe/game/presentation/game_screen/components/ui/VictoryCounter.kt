package com.rafael.tictactoe.game.presentation.game_screen.components.ui

import android.content.res.Configuration
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.utils.draws.drawCircleinCounter
import com.rafael.tictactoe.game.presentation.utils.draws.drawXinCounter
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun VictoryCounter() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val player1Wins by viewModel.player1Wins.collectAsState()
    val player2Wins by viewModel.player2Wins.collectAsState()
    val isXFor1 by viewModel.isXFor1.collectAsState()
    val xColor by settingsVM.xColor.collectAsState()
    val circleColor by settingsVM.circleColor.collectAsState()
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)

    Box(
        modifier = Modifier
            .fillMaxSize(),
          contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
           .fillMaxWidth(if (isInLandscape) 0.48f else 1f)
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
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                    ),
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
                            drawXinCounter(xColor)
                        } else {
                            drawCircleinCounter(circleColor)
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.width(6.dp)
                )
                Card(
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                    ),
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
                            drawCircleinCounter(circleColor)
                         } else {
                            drawXinCounter(xColor)
                        }
                    }
                }
            }
        }
    }
}
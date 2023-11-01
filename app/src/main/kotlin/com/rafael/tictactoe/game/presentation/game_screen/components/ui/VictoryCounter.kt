package com.rafael.tictactoe.game.presentation.game_screen.components.ui

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.utils.draws.drawCircleinCounter
import com.rafael.tictactoe.game.presentation.utils.draws.drawXinCounter
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun VictoryCounter(
    state: GameState,
    settingsState: SettingsState
) {
    val player1Wins = state.player1Wins
    val player2Wins = state.player2Wins
    val isXFor1 = state.isXFor1
    val xColor by animateColorAsState(settingsState.xColor)
    val circleColor by animateColorAsState(settingsState.circleColor)
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)
    val countersSize = remember { mutableStateListOf(0,1) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
          contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopCenter
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(if (isInLandscape) 0.48f else 1f)
               // .statusBarsPadding()
                .padding(top = 45.dp)
        ) {
            countersSize.forEach { counter ->
                Box(
                    modifier = Modifier.padding(6.dp)
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
                            text =  when (counter) {
                                0 -> "$player1Wins"
                                1 -> "$player2Wins"
                                else -> ""
                            },
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
                                when (counter) {
                                    0 -> drawXinCounter(xColor)
                                    1 -> drawCircleinCounter(circleColor)
                                    else -> Unit
                                }
                            } else {
                                when (counter) {
                                    0 -> drawCircleinCounter(circleColor)
                                    1 -> drawXinCounter(xColor)
                                    else -> Unit
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
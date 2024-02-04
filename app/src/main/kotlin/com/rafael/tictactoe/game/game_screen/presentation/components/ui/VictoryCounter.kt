package com.rafael.tictactoe.game.gameScreen.presentation.components.ui

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.gameScreen.presentation.util.draws.drawCircleinCounter
import com.rafael.tictactoe.game.gameScreen.presentation.util.draws.drawXinCounter
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState

@Composable
fun VictoryCounter(
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val player1Wins = state.player1Wins
    val player2Wins = state.player2Wins
    val isXFor1 = state.isXFor1
    val xColor by animateColorAsState(settingsState.xColor)
    val circleColor by animateColorAsState(settingsState.circleColor)
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopCenter,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(if (isInLandscape) 0.48f else 1f)
                // .statusBarsPadding()
                .padding(top = 45.dp),
        ) {
            Counters.values().forEachIndexed { _, counter ->
                Box(
                    modifier = Modifier.padding(6.dp),
                ) {
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp),
                        ),
                        modifier = Modifier
                            .width(70.dp)
                            .height(55.dp),
                    ) {
                        Text(
                            text = when (counter) {
                                Counters.FIRST -> "$player1Wins"
                                Counters.SECOND -> "$player2Wins"
                            },
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Canvas(
                                modifier = Modifier
                                    .size(15.dp),
                            ) {
                                if (isXFor1) {
                                    when (counter) {
                                        Counters.FIRST -> drawXinCounter(xColor)
                                        Counters.SECOND -> drawCircleinCounter(circleColor)
                                    }
                                } else {
                                    when (counter) {
                                        Counters.FIRST -> drawCircleinCounter(circleColor)
                                        Counters.SECOND -> drawXinCounter(xColor)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class Counters {
    FIRST,
    SECOND,
}

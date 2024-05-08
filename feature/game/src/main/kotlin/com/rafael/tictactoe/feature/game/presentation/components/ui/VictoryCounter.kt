package com.rafael.tictactoe.game.gameScreen.presentation.components.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.game.domain.model.Counter
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.core.resources.draws.drawCircleinCounter
import com.rafael.tictactoe.core.resources.draws.drawXinCounter
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState

@Composable
fun VictoryCounter(
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val xColor by animateColorAsState(settingsState.xColor)
    val circleColor by animateColorAsState(settingsState.circleColor)
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE
    val counters = listOf(
        Counter(
            playerName = state.player1NameOnCounter,
            playerWins = state.player1Wins,
        ),
        Counter(
            playerName = state.player2NameOnCounter,
            playerWins = state.player2Wins,
        ),
    )

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopCenter,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth(if (isInLandscape) 0.48f else 1f)
                .statusBarsPadding()
                .padding(top = 8.dp),
        ) {
            counters.forEachIndexed { index, counter ->
                Column(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .width(75.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        AnimatedContent(
                            targetState = counter.playerName,
                            transitionSpec = {
                                fadeIn() togetherWith fadeOut()
                            },
                        ) { playerName ->
                            Text(
                                text = playerName,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                            )
                        }
                    }

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
                            text = counter.playerWins.toString(),
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
                                if (state.isXFor1) {
                                    when (index) {
                                        0 -> drawXinCounter(xColor)
                                        1 -> drawCircleinCounter(circleColor)
                                    }
                                } else {
                                    when (index) {
                                        0 -> drawCircleinCounter(circleColor)
                                        1 -> drawXinCounter(xColor)
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

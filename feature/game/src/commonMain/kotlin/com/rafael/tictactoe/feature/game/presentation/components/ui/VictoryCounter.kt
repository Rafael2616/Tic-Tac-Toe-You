package com.rafael.tictactoe.feature.game.presentation.components.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.core.platform.configuration.isInLandscape
import com.rafael.tictactoe.core.resources.draws.drawCircleInCounter
import com.rafael.tictactoe.core.resources.draws.drawXinCounter
import com.rafael.tictactoe.feature.game.domain.model.Counter
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState

@Composable
internal fun VictoryCounter(
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val xColor by animateColorAsState(settingsState.xColor, label = "")
    val circleColor by animateColorAsState(settingsState.circleColor, label = "")
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
                            label = "",
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
                                        1 -> drawCircleInCounter(circleColor)
                                    }
                                } else {
                                    when (index) {
                                        0 -> drawCircleInCounter(circleColor)
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

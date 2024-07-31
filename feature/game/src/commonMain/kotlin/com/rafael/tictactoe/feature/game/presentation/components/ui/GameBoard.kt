package com.rafael.tictactoe.feature.game.presentation.components.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.core.platform.configuration.isInLandscape
import com.rafael.tictactoe.feature.game.domain.model.GameEvent
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.settings.domain.animations.rememberRGBAnimation
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.util.BoardConstants.SHAPE_CUSTOM
import kotlinx.coroutines.delay

@Composable
internal fun GameBoard(
    onEvent: (GameEvent) -> Unit,
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val cardItems = remember { listOf(0, 1, 2) }
    val rowItems = remember { listOf(0, 1, 2) }
    val pPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 50.dp)
    val lPadding = PaddingValues(start = 8.dp, end = 40.dp, bottom = 8.dp)
    val rgbAnimController = rememberRGBAnimation()
    var rgbColorCurrent by remember { mutableStateOf(Color.Red) }
    var rgbColorOld by remember { mutableStateOf(Color.Yellow) }
    val rgbColorCurrentAnimated by animateColorAsState(rgbColorCurrent, label = "")
    val rgbColorOldAnimated by animateColorAsState(rgbColorOld, label = "")
    val rgbGradientBrush = Brush.linearGradient(
        colors = listOf(rgbColorCurrentAnimated, rgbColorOldAnimated),
    )

    LaunchedEffect(settingsState.isRGBEffectOn) {
        rgbAnimController.animate().collect { currentColor ->
            rgbColorCurrent = currentColor
            delay(125L)
            rgbColorOld = currentColor
            delay(125L)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(if (isInLandscape) lPadding else pPadding),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = if (isInLandscape) Alignment.End else Alignment.CenterHorizontally,
    ) {
        rowItems.forEachIndexed { rowIndex, _ ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(if (isInLandscape) 0.52f else 1f)
                    .padding(2.dp),
            ) {
                cardItems.forEachIndexed { cardIndex, _ ->
                    Card(
                        shape = RoundedCornerShape(settingsState.boardCardsCornerRadius.dp),
                        border = if (settingsState.boardCardsThickness > 0f && settingsState.shapeSelected == SHAPE_CUSTOM) {
                            if (settingsState.isRGBEffectOn && settingsState.isGradientOn) {
                                BorderStroke(settingsState.boardCardsThickness.dp, rgbGradientBrush)
                            } else if (settingsState.isRGBEffectOn && !settingsState.isGradientOn) {
                                BorderStroke(settingsState.boardCardsThickness.dp, rgbColorCurrentAnimated)
                            } else {
                                BorderStroke(settingsState.boardCardsThickness.dp, settingsState.boardCardsBorderColor)
                            }
                        } else {
                            null
                        },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(settingsState.boardCardsElevation.dp),
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(6.dp)
                            .size(114.dp)
                            .shadow(settingsState.boardCardsShadow.dp, RoundedCornerShape(settingsState.boardCardsCornerRadius.dp))
                            .clip(RoundedCornerShape(settingsState.boardCardsCornerRadius.dp))
                            .clickable {
                                onEvent(GameEvent.OnCardClick(rowIndex, cardIndex))
                            },
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterHorizontally),
                            contentAlignment = Alignment.Center,
                        ) {
                            Box(
                                modifier = Modifier.size(95.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                DrawXorCircle(
                                    rowIndex,
                                    cardIndex,
                                    state,
                                    settingsState,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

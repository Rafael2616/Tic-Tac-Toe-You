package com.rafael.tictactoe.game.presentation.game_screen.components.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.utils.draws.drawX
import com.rafael.tictactoe.game.presentation.utils.draws.drawAnCircle
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun DrawXorCircle(
    rowIndex: Int,
    cardIndex: Int,
    state: GameState,
    settingsState: SettingsState
) {
    val card = state.card
	val isCardClicked = state.isCardClicked
    val xColor = settingsState.xColor
    val circleColor = settingsState.circleColor

    AnimatedVisibility(
        visible = isCardClicked[rowIndex][cardIndex],
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
        	if (card[rowIndex][cardIndex]) {
                drawX(xColor)
            } else {
                drawAnCircle(circleColor)
            }
        }
    }
}
package com.rafael.tictactoe.feature.game.presentation.components.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rafael.tictactoe.core.resources.draws.drawAnCircle
import com.rafael.tictactoe.core.resources.draws.drawX
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState

@Composable
internal fun DrawXorCircle(
    rowIndex: Int,
    cardIndex: Int,
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val card = state.card
    val isCardClicked = state.isCardClicked
    val xColor = settingsState.xColor
    val circleColor = settingsState.circleColor

    AnimatedVisibility(
        visible = isCardClicked[rowIndex][cardIndex],
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut(),
    ) {
        Canvas(
            modifier = modifier
                .fillMaxSize(),
        ) {
            if (card[rowIndex][cardIndex]) {
                drawX(xColor)
            } else {
                drawAnCircle(circleColor)
            }
        }
    }
}

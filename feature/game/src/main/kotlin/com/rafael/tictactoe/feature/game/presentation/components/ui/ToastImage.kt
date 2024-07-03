package com.rafael.tictactoe.feature.game.presentation.components.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rafael.tictactoe.core.resources.draws.drawCircleInToast
import com.rafael.tictactoe.core.resources.draws.drawXinToast
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState

@Composable
internal fun ToastImage(
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val xColor by animateColorAsState(settingsState.xColor)
    val circleColor by animateColorAsState(settingsState.circleColor)
    val play = state.play
    val isXFor1 = state.isXFor1
    val is1Win = state.is1Win
    val hasWinner = state.hasWinner
    val isXImage = state.isXImage

    Canvas(
        modifier = modifier
            .fillMaxSize(),
    ) {
        // Image showed when game not started yet (configuration fase)
        if (!play) {
            if (isXFor1) drawXinToast(xColor) else drawCircleInToast(circleColor)
        }

        // Image for case of game is on going
        if (play && !hasWinner) {
            if (isXImage) drawXinToast(xColor) else drawCircleInToast(circleColor)
        }

        // Image for case of has winner
        if (hasWinner) {
            if (is1Win && isXFor1) {
                drawXinToast(xColor)
            } else if (is1Win && !isXFor1) {
                drawCircleInToast(circleColor)
            } else if (!is1Win && isXFor1) {
                drawCircleInToast(circleColor)
            } else if (!is1Win && !isXFor1) {
                drawXinToast(xColor)
            }
        }
    }
}

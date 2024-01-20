package com.rafael.tictactoe.game.domain.restart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState

@Composable
fun RestartButtonLogic(
    onEvent: (GameEvent) -> Unit,
    state: GameState,
) {
    val isCardClicked = state.isCardClicked
    val isGameFinished = state.isGameFinished
    val play = state.play

    if (isCardClicked.flatten().all { !it } || isGameFinished || !play) {
        onEvent(GameEvent.ShowRestartButton(false))
    } else {
        onEvent(GameEvent.ShowRestartButton(true))
    }
}

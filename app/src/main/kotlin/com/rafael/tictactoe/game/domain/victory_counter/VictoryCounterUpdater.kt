package com.rafael.tictactoe.game.domain.victoryCounter

import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState

fun victoryCounterUpdater(
    state: GameState,
    onEvent: (GameEvent) -> Unit,
) {
    val isScoreUp = state.isScoreUp
    val is1Win = state.is1Win
    val player1Wins = state.player1Wins
    val player2Wins = state.player2Wins

    if (!isScoreUp) {
        if (is1Win) {
            onEvent(GameEvent.UpdatePlayer1Wins(player1Wins + 1))
        } else {
            onEvent(GameEvent.UpdatePlayer2Wins(player2Wins + 1))
        }
        onEvent(GameEvent.UpdateIsScoreUp(true))
    }
}

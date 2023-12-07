package com.rafael.tictactoe.game.domain.victoryCounter

import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun victoryCounterUpdater(
    vm: GameViewModel,
    state: GameState,
) {
    val isScoreUp = state.isScoreUp
    val is1Win = state.is1Win
    val player1Wins = state.player1Wins
    val player2Wins = state.player2Wins

    if (!isScoreUp) {
        if (is1Win) {
            vm.updatePlayer1Wins(player1Wins + 1)
        } else {
            vm.updatePlayer2Wins(player2Wins + 1)
        }
        vm.updateIsScoreUp(true)
    }
}

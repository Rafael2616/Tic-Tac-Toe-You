package com.rafael.tictactoe.game.domain.checkers

import androidx.compose.runtime.Composable
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.victory_counter.VictoryCounterUpdater
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun WinnerChecker(
    vm: GameViewModel,
    state: GameState
) {
    val card = state.card
    val isCardClicked = state.isCardClicked
    val isXFor1 = state.isXFor1

    val winningPositions = listOf(
        // Lines
        listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2)),
        listOf(Pair(1, 0), Pair(1, 1), Pair(1, 2)),
        listOf(Pair(2, 0), Pair(2, 1), Pair(2, 2)),

        // Columns
        listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0)),
        listOf(Pair(0, 1), Pair(1, 1), Pair(2, 1)),
        listOf(Pair(0, 2), Pair(1, 2), Pair(2, 2)),

        // Diagonals
        listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)),
        listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0))
    )

    for ((index, positions) in winningPositions.withIndex()) {
        val areAllClicked = positions.all { (x, y) -> isCardClicked[x][y] }
        val areAllClickedByPlayer1 = positions.all { (x, y) -> card[x][y] }
        val areAllClickedByPlayer2 = positions.all { (x, y) -> !card[x][y] }

        if (areAllClicked) {
            if (areAllClickedByPlayer1) {
                vm.updateHasWinner(true)
                if (isXFor1) {
                    vm.updateIs1Win(true)
                } else {
                    vm.updateIs1Win(false)
                }
                vm.updateWinningPositionsIndices(index)
            }
            if (areAllClickedByPlayer2) {
                vm.updateHasWinner(true)
                if (isXFor1) {
                    vm.updateIs1Win(false)
                } else {
                    vm.updateIs1Win(true)
                }
                vm.updateWinningPositionsIndices(index)
            }
        }
    }

    if (state.hasWinner) {
        vm.updateIsGameFinished(true)
        vm.showVictoryCounterReseter(true)
        VictoryCounterUpdater(vm, state)
        vm.execWinnerSound()
        vm.updateHasLastWinningPlayer(true)
    }
}
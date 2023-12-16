package com.rafael.tictactoe.game.domain.checkers

import androidx.compose.runtime.Composable
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState

@Composable
fun WinnerChecker(
    onEvent: (GameEvent) -> Unit,
    state: GameState,
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
        listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0)),
    )

    for ((index, positions) in winningPositions.withIndex()) {
        val areAllClicked = positions.all { (x, y) -> isCardClicked[x][y] }
        val areAllClickedByPlayer1 = positions.all { (x, y) -> card[x][y] }
        val areAllClickedByPlayer2 = positions.all { (x, y) -> !card[x][y] }

        if (areAllClicked) {
            if (areAllClickedByPlayer1) {
                onEvent(GameEvent.UpdateHasWinner(true))
                if (isXFor1) {
                    onEvent(GameEvent.UpdateIs1Win(true))
                } else {
                    onEvent(GameEvent.UpdateIs1Win(false))
                }
                onEvent(GameEvent.UpdateWinningPositionsIndices(index))
            }
            if (areAllClickedByPlayer2) {
                onEvent(GameEvent.UpdateHasWinner(true))
                if (isXFor1) {
                    onEvent(GameEvent.UpdateIs1Win(false))
                } else {
                    onEvent(GameEvent.UpdateIs1Win(true))
                }
                onEvent(GameEvent.UpdateWinningPositionsIndices(index))
            }
        }
    }

    if (state.hasWinner) {
        onEvent(GameEvent.GameIsFinished(true))
        onEvent(GameEvent.ShowVictoryCounterReseter(true))
        onEvent(GameEvent.UpdateHasLastWinningPlayer(true))
    }
}

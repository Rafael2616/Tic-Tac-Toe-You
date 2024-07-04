package com.rafael.tictactoe.feature.game.domain.checkers

import com.rafael.tictactoe.feature.game.domain.model.GameState

internal class Checkers {
    private val winningPositions = listOf(
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

    fun checkWinner(state: GameState): Triple<Boolean, Int, List<Int>> {
        var winner = -1
        var hasWinner = false
        val winningPositionsIndices = mutableListOf<Int>()

        for ((index, positions) in winningPositions.withIndex()) {
            val areAllClicked = positions.all { (x, y) -> state.isCardClicked[x][y] }
            val areAllClickedByPlayer1 = positions.all { (x, y) -> state.card[x][y] }
            val areAllClickedByPlayer2 = positions.all { (x, y) -> !state.card[x][y] }

            if (areAllClicked && areAllClickedByPlayer1) {
                winningPositionsIndices.add(index)
                hasWinner = true
                winner = if (state.isXFor1) {
                    1
                } else {
                    2
                }
            }
            if (areAllClicked && areAllClickedByPlayer2) {
                winningPositionsIndices.add(index)
                hasWinner = true
                winner = if (state.isXFor1) {
                    2
                } else {
                    1
                }
            }
        }
        return Triple(hasWinner, winner, winningPositionsIndices)
    }

    fun checkIfGotOld(state: GameState): Boolean {
        val hasWinner = checkWinner(state).first
        return (state.isCardClicked.flatten().all { it }) && (!hasWinner)
    }

    fun checkGameFinished(state: GameState): Boolean {
        val gotOld = checkIfGotOld(state)
        val hasWinner = checkWinner(state).first

        return gotOld && !hasWinner || hasWinner
    }

    fun hasLastWinner(state: GameState): Boolean {
        val hasWinner = checkWinner(state).first
        if (hasWinner) return true
        return state.hasLastWinningPlayer
    }

    fun whoPlayerWin(state: GameState): Boolean {
        val is1Win = checkWinner(state).second
        return is1Win == 1
    }
}

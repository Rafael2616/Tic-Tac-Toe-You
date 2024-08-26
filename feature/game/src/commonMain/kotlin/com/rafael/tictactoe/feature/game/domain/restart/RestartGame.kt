package com.rafael.tictactoe.feature.game.domain.restart

import com.rafael.tictactoe.feature.game.domain.model.GameState

class RestartGame {
    /*
     * Defines which image will be showed in when next match
     * based on image of winner of previous match, for eg.
     */
    fun resetXImage(state: GameState): Boolean {
        val is1Win = state.is1Win
        val isXFor1 = state.isXFor1
        var isXImage = state.isXImage

        if (state.hasWinner || state.hasLastWinningPlayer) {
            isXImage = if (is1Win && isXFor1) {
                true
            } else if (!is1Win && !isXFor1) {
                true
            } else {
                false
            }
        }

        if (!state.hasWinner && !state.hasLastWinningPlayer) {
            isXImage = isXFor1
        }
        return isXImage
    }

    /*
     * Defines which player will start the new match based on factors such as;
     * if there have been past winners and who was the last winner
     */
    fun resetPlayer(state: GameState): Boolean = resetXImage(state) == state.isXFor1

    fun resetIsPlayerCPU(state: GameState): Boolean {
        val is1Win = state.is1Win
        val isCPUPlayer1 = state.isCPUPlayer1
        val hasWinner = state.hasWinner
        val isCPU: Boolean

        if (hasWinner) {
            isCPU = if (is1Win && isCPUPlayer1) {
                true
            } else if (!is1Win && !isCPUPlayer1) {
                true
            } else {
                false
            }
        } else {
            isCPU = if (!state.hasLastWinningPlayer) {
                isCPUPlayer1
            } else {
                if (isCPUPlayer1 && is1Win) {
                    true
                } else if (!isCPUPlayer1 && !is1Win) {
                    true
                } else {
                    false
                }
            }
        }
        return isCPU
    }

    fun shouldShowRestartButton(state: GameState): Boolean {
        val isCardClicked = state.isCardClicked
        val isGameFinished = state.isGameFinished
        val play = state.play

        return !(isCardClicked.flatten().all { !it } || isGameFinished || !play)
    }
}

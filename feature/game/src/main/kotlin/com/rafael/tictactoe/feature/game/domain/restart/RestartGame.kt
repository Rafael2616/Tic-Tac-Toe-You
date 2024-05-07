package com.rafael.tictactoe.game.domain.restart

import com.rafael.tictactoe.game.domain.model.GameState

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
            if (is1Win && isXFor1) {
                isXImage = true
            } else if (!is1Win && !isXFor1) {
                isXImage = true
            } else {
                isXImage = false
            }
        }

        if (!state.hasWinner && !state.hasLastWinningPlayer) {
            if (isXFor1) {
                isXImage = true
            } else {
                isXImage = false
            }
        }
        return isXImage
    }

    /*
     * Defines which player will start the new match based on factors such as;
     * if there have been past winners and who was the last winner
     */
    fun resetPlayer(state: GameState): Boolean {
        return resetXImage(state) == state.isXFor1
    }

    fun resetIsPlayerCPU(state: GameState): Boolean {
        val is1Win = state.is1Win
        val isCPUPlayer1 = state.isCPUPlayer1
        val hasWinner = state.hasWinner
        var isCPU: Boolean

        if (hasWinner) {
            if (is1Win && isCPUPlayer1) {
                isCPU = true
            } else if (!is1Win && !isCPUPlayer1) {
                isCPU = true
            } else {
                isCPU = false
            }
        } else {
            if (!state.hasLastWinningPlayer) {
                if (isCPUPlayer1) {
                    isCPU = true
                } else {
                    isCPU = false
                }
            } else {
                if (isCPUPlayer1 && is1Win) {
                    isCPU = true
                } else if (!isCPUPlayer1 && !is1Win) {
                    isCPU = true
                } else {
                    isCPU = false
                }
            }
        }
        return isCPU
    }

    fun shouldShowRestartButton(state: GameState): Boolean {
        val isCardClicked = state.isCardClicked
        val isGameFinished = state.isGameFinished
        val play = state.play

        if (isCardClicked.flatten().all { !it } || isGameFinished || !play) {
            return false
        }
        return true
    }
}

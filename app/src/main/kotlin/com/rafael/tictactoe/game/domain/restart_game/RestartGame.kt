package com.rafael.tictactoe.game.domain.restart_game

import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun RestartGame(
    vm: GameViewModel,
    state: GameState
) {
    val allFalse = List(3){List<Boolean>(3) { false }}
    val is1Win = state.is1Win
    val isXFor1 = state.isXFor1
    val isCPUPlayer1 = state.isCPUPlayer1
    val hasWinner = state.hasWinner
    val hasLastWinningPlayer = state.hasLastWinningPlayer
    val gameMode = state.gameMode

    /*
     * Defines which image will be showed in when next match
     * based on image of winner of previous match, for eg.
     */
    if (hasWinner || hasLastWinningPlayer) {
        if (is1Win && isXFor1) {
            vm.updateIsXImage(true)
        } else if (!is1Win && !isXFor1) {
            vm.updateIsXImage(true)
        } else {
            vm.updateIsXImage(false)
        }
    }

    if (!hasLastWinningPlayer) {
        if (isXFor1) {
            vm.updateIsXImage(true)
        } else {
            vm.updateIsXImage(false)
        }
    }

    /*
     * Defines which player will start the new match based on factors such as; 
     * if there have been past winners and who was the last winner
     */
    if (hasWinner || hasLastWinningPlayer) {
        if (is1Win) {
            vm.updateIsPlayer1(true)
        } else {
            vm.updateIsPlayer1(false)
        }
    }

    if (!hasWinner && !hasLastWinningPlayer) {
        vm.updateIsPlayer1(true)
    }

    when (gameMode) {
        GameMode.VsCPU -> {
            if (hasWinner) {
                 if (is1Win && isCPUPlayer1) {
                    vm.updateIsCPU(true)
                } else if (!is1Win && !isCPUPlayer1) {
                    vm.updateIsCPU(true)
                } else {
                    vm.updateIsCPU(false)
                }
            }

            if (!hasWinner) {
                if (!hasLastWinningPlayer) {
                    if (isCPUPlayer1) {
                        vm.updateIsCPU(true)
                    } else {
                        vm.updateIsCPU(false)
                    }
                } else {
                    if (isCPUPlayer1 && is1Win) {
                        vm.updateIsCPU(true)
                    } else if (!isCPUPlayer1 && !is1Win) {
                        vm.updateIsCPU(true)
                    } else {
                        vm.updateIsCPU(false)
                    }
                }
            }
        }
        else -> {
        /*
         * All actions related to PvP and Online mode are the same
         * as those being shared in the code above
         */
        }
    }

    //Others common actions, executed in all cases
    vm.updateHasWinner(false)
    vm.updateGotOld(false)
    vm.updateIsGameFinished(false)
    vm.updateAllIsCardClicked(allFalse)
    vm.updateIsScoreUp(false)
    vm.updateIsWinSoundPlaying(false)
    vm.updateIsGotOldSoundPlaying(false)
    vm.resetWinningPositionsIndices(emptyList())
    vm.showRestartButton(false)
}
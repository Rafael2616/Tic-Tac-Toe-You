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
    val isCPUMode = state.isCPUMode
    val isCPUPlayer1 = state.isCPUPlayer1
    val winner = state.winner
    val hasLastWinningPlayer = state.hasLastWinningPlayer
    val localPlayer1 = state.localPlayer1
    val isPlayer1 = state.isPlayer1
    var gameMode: GameMode = GameMode.PvP
    
    if (isCPUMode) {
        gameMode = GameMode.VsCPU
    } else {
        gameMode = GameMode.PvP
    }

     /*
     *Defines which player will start next match in PvP Mode
     *based on who was the winner in the previous match
     */

      when (gameMode) {
         GameMode.PvP -> {
            if (winner) {
                if (is1Win) {
                    vm.updateIsPlayer1(true)
                } else {
                    vm.updateIsPlayer1(false)
                }
                vm.updateLastWinningPlayer(true)
            }

            if (!winner) {
                if (!hasLastWinningPlayer) {
                    vm.updateIsPlayer1(true)
                } else {
                    if (is1Win) {
                        vm.updateIsPlayer1(true)
                    } else {
                        vm.updateIsPlayer1(false)
                    }
                }
            }
        }
        GameMode.VsCPU -> {
            if (winner) {
                if (is1Win) {
                    vm.updateIsPlayer1(true)
                } else {
                    vm.updateIsPlayer1(false)
                }
                 if (is1Win && isCPUPlayer1) {
                    vm.updateIsCPU(true)
                } else if (!is1Win && isCPUPlayer1) {
                    vm.updateIsCPU(false)
                } else if (is1Win && !isCPUPlayer1) {
                    vm.updateIsCPU(false)
                } else {
                    vm.updateIsCPU(true)
                }
            }

            if (!winner) {
                if (!hasLastWinningPlayer) {
                    if (isCPUPlayer1) {
                        vm.updateIsCPU(true)
                    } else {
                        vm.updateIsCPU(false)
                    }
                    vm.updateIsPlayer1(true)
                } else {
                    if (isCPUPlayer1 && isPlayer1) {
                        vm.updateIsCPU(true)
                    } else if (!isCPUPlayer1 && !isPlayer1) {
                        vm.updateIsCPU(true)
                    } else {
                        vm.updateIsCPU(false)
                    }
                    if (is1Win) {
                        vm.updateIsPlayer1(true)
                    } else {
                        vm.updateIsPlayer1(false)
                    }
                }
            }
        }
    }

    //Common actions, executed in all cases
    vm.updateWinner(false)
    vm.updateGotOld(false)
    vm.updateIsGameFinished(false)
    vm.updateAllIsCardClicked(allFalse)
    vm.updateIsScoreUp(false)
    vm.updateIsWinSoundPlaying(false)
    vm.updateIsGotOldSoundPlaying(false)
    vm.resetWinningPositionsIndices(emptyList())
    vm.showRestartButton(false)
    println("$state")
}
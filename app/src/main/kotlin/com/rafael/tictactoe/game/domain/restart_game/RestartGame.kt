package com.rafael.tictactoe.game.domain.restart_game

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

    /*
     *Defines which player will start next match in PvP Mode
     *based on who was the winner in the previous match
     */
    if (is1Win) {
        vm.updateIsPlayer1(true)
    } else {
        vm.updateIsPlayer1(false)
    }
    
    /*
     *Defines which image will be showed in when next match starts
     *based on image of winner of previous match
     */
    if (is1Win && isXFor1) {
        vm.updateIsXImage(true)
    } else if (is1Win && !isXFor1){
        vm.updateIsXImage(false)
    } else if (!is1Win && isXFor1) {
        vm.updateIsXImage(false)
    } else {
        vm.updateIsXImage(true)
    }

    /*
     *Defines which player will start next match in CPU Mode, 
     *considering the possibility of the player being the CPU player,
     *based on who was the winner in the previous match
     */       
    if (isCPUMode) {
        if (is1Win && isCPUPlayer1) {
            vm.updateIsCPU(true)
        } else if (is1Win && !isCPUPlayer1) {
            vm.updateIsCPU(false)
        } else if (!is1Win && isCPUPlayer1) {
            vm.updateIsCPU(false)
        } else {
            vm.updateIsCPU(true)
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
}
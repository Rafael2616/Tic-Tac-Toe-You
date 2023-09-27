package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun RestartGame(vm: GameViewModel) {
    val allFalse = List(3){List<Boolean>(3) { false }}
    val is1Win = vm.is1Win.value
    val isXFor1 = vm.isXFor1.value
    val isCPUMode = vm.isCPUMode.value
    val isCPUPlayer1 = vm.isCPUPlayer1.value

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

    // Common actions, executed in any case
    vm.updateWinner(false)
    vm.updateGotOld(false)
    vm.updateIsGameFinished(false)
    vm.updateAllIsCardClicked(allFalse)
    vm.updateAllCards(allFalse)
    vm.updateIsScoreUp(false)
    vm.updateIsWinSoundPlaying(false)
    vm.updateIsGotOldSoundPlaying(false)
    vm.resetWinningPositionsIndices(emptyList())
}
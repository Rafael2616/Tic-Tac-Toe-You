package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

/*
 *This is a workaround while i dont discover a way to restart
 *ViewModel to default values
*/

fun exitGameParams(vm: GameViewModel) {
    vm.resetVictoryCounter()
    vm.showExitGameDialog(false)
    vm.updatePlay(false)
    vm.updatePlayer1Name("")
    vm.updatePlayer2Name("")
    vm.showPlayer1Name("")
    vm.showPlayer2Name("")
    vm.updateLocalPlayer1(true)
    vm.updateIsPlayer1(true)
    vm.updateIsCPUPlayer1(false)
    vm.updateIsCPU(false)
    vm.updateIsXImage(true)
    vm.updateIsXFor1(true)
}
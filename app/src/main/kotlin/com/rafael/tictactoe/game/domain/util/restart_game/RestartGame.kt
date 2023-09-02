package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun RestartGame(vm: GameViewModel) {
    val allFalse = List(3){List<Boolean>(3) { false }}
    val is1Win = vm.is1Win.value

    if (is1Win) {
        vm.updateIsPlayer1(true)
    } else {
        vm.updateIsPlayer1(false)
    }

    vm.updateWinner(false)
    vm.updateGotOld(false)
    vm.updateIsGameFinished(false)
    vm.updateAllIsCardClicked(allFalse)
    vm.updateAllCards(allFalse)
    vm.updateIsScoreUp(false)
    vm.updateIsWinSoundPlaying(false)
    vm.updateIsGotOldSoundPlaying(false)
    vm.setCoordinates(0f, 0f, 0f, 0f)
}

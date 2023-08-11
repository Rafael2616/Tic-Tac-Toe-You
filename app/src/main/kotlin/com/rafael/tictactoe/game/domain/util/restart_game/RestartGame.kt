package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun RestartGame(vm: GameViewModel) {
	 val allFalse = List<Boolean>(9) { false }

  	vm.updateWinner(false)
  	vm.updateGotOld(false)
    vm.updateIsGameFinished(false)
    vm.updateAllIsCardClicked(allFalse)
    vm.updateAllCards(allFalse)
   	vm.updateIsScoreUp(false)
    vm.updateIs1Win(false)
    vm.updateIsWinSoundPlaying(false)
    vm.updateIsGotOldSoundPlaying(false)
	  vm.setCoordinates(0f, 0f, 0f, 0f)
}

package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun RestartButtonLogic(vm: GameViewModel) {

    val isCardClicked = vm.isCardClicked.value
    val isGameFinished = vm.isGameFinished.value

	if ((isCardClicked.flatten().all {!it}) || (isGameFinished)) {
  	  vm.updateShowRestartButton(false)
	} else {
   	 vm.updateShowRestartButton(true)
	}
}
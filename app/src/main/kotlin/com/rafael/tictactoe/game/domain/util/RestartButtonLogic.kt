package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun RestartButtonLogic(
vm: GameViewModel,
isCardClicked: List<Boolean>,
winner: Boolean,
gotOld: Boolean
) {

	if ((isCardClicked.all {!it}) || (winner) || (gotOld)) {
  	  vm.updateShowRestartButton(false)
	} else {
   	 vm.updateShowRestartButton(true)
	}
}
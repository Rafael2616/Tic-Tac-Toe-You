package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun GotOldChecker(vm: GameViewModel) {
    val gotOld = vm.gotOld.value
    val winner = vm.winner.value
    val isCardClicked = vm.isCardClicked.value

	if ((isCardClicked.all { it }) && (!winner)) {
		vm.updateGotOld(true)
    } else {
		vm.updateGotOld(false)
	}
    
    if(gotOld) {
        vm.updateIsGameFinished(true)
    }
}
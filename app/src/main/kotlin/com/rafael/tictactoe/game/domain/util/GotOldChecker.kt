package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun GotOldChecker(
vm: GameViewModel,
isCardClicked: List<Boolean>,
winner: Boolean
) {

	if ((isCardClicked.all { it }) && (!winner)) {
		vm.updateGotOld(true)
    }
}
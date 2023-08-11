package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.util.VictoryCounterUpdater
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun WinnerChecker(
    vm: GameViewModel,
    cards: List<Boolean>,
    isCardClicked: List<Boolean>
) {
    val winner = vm.winner.value

	val winningPositions = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(0, 4, 8),
        listOf(2, 4, 6)
   )
	for (positions in winningPositions) {
		if (positions.all { (isCardClicked.get(it) == true) && (cards.get(it) == false )}) {
			vm.updateWinner(true)
   		 vm.updateIs1Win(false)
		} else {
			if (positions.all { (isCardClicked.get(it) == true) && (cards.get(it) == true )}) {
	   		 vm.updateWinner(true)
     	       vm.updateIs1Win(true)
			}
		}
	}

    if (winner) {
        vm.updateIsGameFinished(true)
        VictoryCounterUpdater(vm)
    }
}

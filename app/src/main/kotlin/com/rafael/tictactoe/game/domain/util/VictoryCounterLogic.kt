package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun VictoryCounterLogic(
vm: GameViewModel,
isScoreUp: Boolean,
winner: Boolean,
is1Win: Boolean,
player1Wins: Int,
player2Wins: Int
) {

	if (!isScoreUp) {
		if (winner) {
			vm.updateIsScoreUp(true)
			if (is1Win) {
	   	     vm.updatePlayer1Wins(player1Wins + 1)
			} else {
	 	       vm.updatePlayer2Wins(player2Wins + 1)
			}
		}
	}
}
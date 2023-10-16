package com.rafael.tictactoe.game.domain.victory_counter

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun VictoryCounterUpdater(vm: GameViewModel) {
    val isScoreUp = vm.isScoreUp.value
    val is1Win = vm.is1Win.value
    val player1Wins = vm.player1Wins.value
    val player2Wins = vm.player2Wins.value

    if (!isScoreUp) {
        if (is1Win) {
            vm.updatePlayer1Wins(player1Wins + 1)
		} else {
            vm.updatePlayer2Wins(player2Wins + 1)
		}
        vm.updateIsScoreUp(true)
	}
}
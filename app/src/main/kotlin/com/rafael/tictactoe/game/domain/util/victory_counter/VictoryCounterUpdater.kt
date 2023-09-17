package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun VictoryCounterUpdater(vm: GameViewModel) {
    val isScoreUp = vm.isScoreUp.value
    val is1Win = vm.is1Win.value
    val player1Wins = vm.player1Wins.value
    val player2Wins = vm.player2Wins.value
    val localPlayer1 = vm.localPlayer1.value

    val localPlayer1Wins = if (localPlayer1) {
        player1Wins
    } else {
        player2Wins
    }

    val localPlayer2Wins = if (localPlayer1) {
        player2Wins
    } else {
        player1Wins
    }

    if (!isScoreUp) {
        if (is1Win) {
            vm.updatePlayer1Wins(localPlayer1Wins + 1)
		} else {
            vm.updatePlayer2Wins(localPlayer2Wins + 1)
		}
        vm.updateIsScoreUp(true)
	}
}
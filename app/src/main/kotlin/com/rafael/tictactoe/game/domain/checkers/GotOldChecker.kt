package com.rafael.tictactoe.game.domain.checkers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.rafael.tictactoe.game.domain.audio.GotOldSound
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun GotOldChecker(
    vm: GameViewModel,
    state: GameState
) {
    val isCardClicked = state.isCardClicked
    val winner = state.winner
    val player1Wins = state.player1Wins
    val player2Wins = state.player2Wins
    val isCounterReset by remember {
        derivedStateOf { player1Wins + player2Wins }
    }

	if ((isCardClicked.flatten().all { it }) && (!winner)) {
		vm.updateGotOld(true)
    } else {
		vm.updateGotOld(false)
	}

    val hasGotOld = state.gotOld

    if (hasGotOld && !winner) {
        vm.updateIsGameFinished(true)
        vm.execGotOldSound()
        if (isCounterReset > 0) {
            vm.showVictoryCounterReseter(true)
        }
    }
}
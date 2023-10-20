package com.rafael.tictactoe.game.domain.checkers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.rafael.tictactoe.game.domain.audio.GotOldSound
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun GotOldChecker(vm: GameViewModel) {
    val isCardClicked by vm.isCardClicked.collectAsState()   
    val winner by vm.winner.collectAsState()
    val player1Wins by vm.player1Wins.collectAsState()
    val player2Wins by vm.player2Wins.collectAsState()
    val isCounterReset by remember {
        derivedStateOf { player1Wins + player2Wins }
    }

	if ((isCardClicked.flatten().all { it }) && (!winner)) {
		vm.updateGotOld(true)
    } else {
		vm.updateGotOld(false)
	}

    val hasGotOld by vm.gotOld.collectAsState()

    if (hasGotOld && !winner) {
        vm.updateIsGameFinished(true)
        vm.execGotOldSound()
        if (isCounterReset > 0) {
            vm.showVictoryCounterReseter(true)
        }
    }
}
package com.rafael.tictactoe.game.domain.checkers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com.rafael.tictactoe.game.domain.audio.GotOldSound
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun GotOldChecker(vm: GameViewModel) {
    val isCardClicked by vm.isCardClicked.collectAsState()   
    val winner by vm.winner.collectAsState()

	if ((isCardClicked.flatten().all { it }) && (!winner)) {
		vm.updateGotOld(true)
    } else {
		vm.updateGotOld(false)
	}

    val hasGotOld by vm.gotOld.collectAsState()

    if (hasGotOld && !winner) {
        vm.updateIsGameFinished(true)
        vm.execGotOldSound()
    }
}
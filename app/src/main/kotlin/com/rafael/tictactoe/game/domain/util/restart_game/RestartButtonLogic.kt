package com.rafael.tictactoe.game.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun RestartButtonLogic(vm: GameViewModel) {
    val isCardClicked by vm.isCardClicked.collectAsState()   
    val isGameFinished by vm.isGameFinished.collectAsState()   

	if ((isCardClicked.flatten().all {!it}) || (isGameFinished)) {
  	  vm.updateShowRestartButton(false)
	} else {
   	 vm.updateShowRestartButton(true)
	}
}
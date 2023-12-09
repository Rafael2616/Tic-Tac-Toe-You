package com.rafael.tictactoe.game.domain.restart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.gameScreen.viewmodels.GameViewModel

@Composable
fun RestartButtonLogic(vm: GameViewModel, state: GameState) {
    val isCardClicked = state.isCardClicked
    val isGameFinished = state.isGameFinished
    val play = state.play

    if (isCardClicked.flatten().all { !it } || isGameFinished || !play) {
        vm.showRestartButton(false)
    } else {
        vm.showRestartButton(true)
    }
}

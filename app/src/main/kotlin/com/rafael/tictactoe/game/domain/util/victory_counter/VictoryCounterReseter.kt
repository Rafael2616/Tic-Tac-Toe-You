package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun VictoryCounterReseter(vm: GameViewModel) {

    vm.updatePlayer1Wins(0)
    vm.updatePlayer2Wins(0)
}

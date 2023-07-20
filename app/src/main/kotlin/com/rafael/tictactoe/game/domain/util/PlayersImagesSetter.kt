package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun PlayersImagesSetter(
vm: GameViewModel,
play: Boolean,
isXFor1: Boolean
) {

	if (!play){
		if (isXFor1){
            vm.updateIsXImage(true)
	} else {
			vm.updateIsXImage(false)
		}
    }
}
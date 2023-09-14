package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

fun PlayersImagesSetter(
    viewModel: GameViewModel
) {
	val play = viewModel.play.value
    val isXFor1 = viewModel.isXFor1.value

	if (!play){
		if (isXFor1) {
            viewModel.updateIsXImage(true)
	} else {
			viewModel.updateIsXImage(false)
		}
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2

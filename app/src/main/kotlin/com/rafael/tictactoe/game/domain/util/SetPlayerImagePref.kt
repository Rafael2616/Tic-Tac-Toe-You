package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun SetPlayerImagePref(repository: GameRepository) {

    val game = repository.getGameValues()
	val play = game.play
	val isXFor1 = game.isXFor1

	if (!play){
		if (isXFor1){
            game.isXImage = true
			repository.setGameValues(game)
	} else {
			game.isXImage = false
			repository.setGameValues(game)
		}
    }
}
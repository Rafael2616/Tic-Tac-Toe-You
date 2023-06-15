package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository

suspend fun SetPlayerImagePref(repository: GameRepository) {

	val gameDefaults = GameEntity.defaultGameValues()
    val game = repository.getGameValues() ?: gameDefaults
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
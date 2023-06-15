package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository

suspend fun ExpandButtonLogic(repository: GameRepository) {

	val gameDefaults = GameEntity.defaultGameValues()
	val game = repository.getGameValues() ?: gameDefaults
	game.expandButton = !game.expandButton
	repository.setGameValues(game)
}
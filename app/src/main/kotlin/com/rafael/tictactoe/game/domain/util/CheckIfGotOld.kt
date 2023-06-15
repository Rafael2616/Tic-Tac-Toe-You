package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository

suspend fun CheckIfGotOld(repository: GameRepository) {

	val gameDefaults = GameEntity.defaultGameValues()
    val game = repository.getGameValues() ?: gameDefaults
    val isCardClicked = game.isCardClicked
	val winner = game.winner

if ((isCardClicked.all { it }) && (!winner)) {
	game.gotOld = false
	repository.setGameValues(game)
    }
}
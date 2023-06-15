package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository

suspend fun ResetVictoryCounterLogic(repository: GameRepository) {

	val gameDefaults = GameEntity.defaultGameValues()
    val game = repository.getGameValues() ?: gameDefaults
    game.player1Wins = 0
    game.player2Wins = 0
	repository.setGameValues(game)
}
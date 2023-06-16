package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository

suspend fun RestartButtonLogic(repository: GameRepository) {

	val gameDefaults = GameEntity.defaultGameValues()
    val game = repository.getGameValues() ?: gameDefaults
	val winner = game.winner
	val gotOld = game.gotOld
	val isCardClicked = game.isCardClicked

	if ((isCardClicked.all {!it}) || (winner) || (gotOld)) {
  	  game.showRestartButton = false
 	   repository.setGameValues(game)
	}
    game.showRestartButton = true
    repository.setGameValues(game)
}
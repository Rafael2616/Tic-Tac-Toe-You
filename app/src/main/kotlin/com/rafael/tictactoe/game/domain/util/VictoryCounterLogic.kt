package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository

suspend fun VictoryCounterLogic(repository: GameRepository) {

    val gameDefaults = GameEntity.defaultGameValues()
	val game = repository.getGameValues() ?: gameDefaults
	val isScoreUp = game.isScoreUp
	val winner = game.winner
	val is1Win = game.is1Win
    val player1Wins = game.player1Wins
    val player2Wins = game.player2Wins

	if (!isScoreUp) {
		if (winner) {
			game.isScoreUp = true
 		   repository.setGameValues(game)
			if (is1Win) {
	   	     game.player1Wins = player1Wins + 1
 			   repository.setGameValues(game)
 		  } else {
	 	       game.player2Wins = player2Wins + 1
 			   repository.setGameValues(game)
			}
		}
	}
}
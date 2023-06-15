package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository

suspend fun CheckForWinner(repository: GameRepository) {

	val gameDefaults = GameEntity.defaultGameValues()
    val game = repository.getGameValues() ?: gameDefaults
    val cards = game.cards
    val isCardClicked = game.isCardClicked

	val winningPositions = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(0, 4, 8),
        listOf(2, 4, 6)
   )
	for (positions in winningPositions) {
		if (positions.all { (isCardClicked.getOrNull(it) == true) &&
		   (cards.getOrNull(it) == false )}) {
			game.winner = true
   		 game.is1Win = false
            repository.setGameValues(game)
		} else {
			if (positions.all { (isCardClicked.getOrNull(it) == true) && 
     	      (cards.getOrNull(it) == true )}) {
	   		 game.winner = true
     	       game.is1Win = true
	  	 	 repository.setGameValues(game)
			}
		}
	}
}
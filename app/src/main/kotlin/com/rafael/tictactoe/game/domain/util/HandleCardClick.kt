package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository

suspend fun HandleCardClick(repository: GameRepository, index: Int) {

	val gameDefaults = GameEntity.defaultGameValues()
    val game = repository.getGameValues() ?: gameDefaults
    val cards = game.cards
    val isCardClicked = game.isCardClicked
	val play = game.play
	val isXImage = game.isXImage
	val winner = game.winner
	val isPlayer1 = game.isPlayer1

if ((play) && (!winner)) {
    if (!isCardClicked[index]) {
        if (isXImage) {
   		//game.isCardClicked[index] = true
           // game.cards[index] = true
            game.isXImage = false
			game.isPlayer1 = !isPlayer1
			repository.setGameValues(game)
        } else {
            //game.isCardClicked[index] = true
            //game.cards[index] = false
            game.isXImage = true
			game.isPlayer1 = !isPlayer1
			repository.setGameValues(game)
       	 }
    	}
	}
 //  game.expandButton = false
 //  repository.setGameValues(game)
}
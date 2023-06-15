package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun HandleCardClick(repository: GameRepository, index: Int) {

    val game = repository.getGameValues()
	val cardsString = repository.getCards()
	val cards = cardsString.map { it.toBoolean() }.toMutableList()
	val isCardClickedString = repository.getIsCardClicked()
	val isCardClicked = isCardClickedString.map { it.toBoolean() }.toMutableList()
	val play = game.play
	val isXImage = game.isXImage
	val winner = game.winner
	val isPlayer1 = game.isPlayer1

if ((play) && (!winner)) {
    if (!isCardClicked[index]) {
        if (isXImage) {
            isCardClicked[index] = true
            repository.setIsCardClicked(isCardClicked)
            cards[index] = true
            repository.setCards(cards)
            game.isXImage = false
			game.isPlayer1 = !isPlayer1
			repository.setGameValues(game)
        } else {
            isCardClicked[index] = true
            repository.setIsCardClicked(isCardClicked)
            cards[index] = false
            repository.setCards(cards)
            game.isXImage = true
			game.isPlayer1 = !isPlayer1
			repository.setGameValues(game)
       	 }
    	}
	}
   game.expandButton = false
   repository.setGameValues(game)
}
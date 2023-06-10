package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*


fun HandleCardClick(
repository: GameRepository,
index: Int
) = runBlocking {

val cardsString = repository.getCards()
val cards = cardsString.map { it.toBoolean() }.toMutableList()
val isCardClickedString = repository.getIsCardClicked()
val isCardClicked = isCardClickedString.map { it.toBoolean() }.toMutableList()
val play = repository.getPlay()
val isXImage = repository.getIsXImage()
val winner = repository.getWinner()
val isPlayer1 = repository.getIsPlayer1()

if ((play) && (!winner)) {
    if (!isCardClicked[index]) {
        if (isXImage) {
            isCardClicked[index] = true
            repository.setIsCardClicked(isCardClicked)
            cards[index] = true
            repository.setCards(cards)
            repository.setIsXImage(false)
            repository.setIsPlayer1(!isPlayer1)
        } else {
            isCardClicked[index] = true
            repository.setIsCardClicked(isCardClicked)
            cards[index] = false
            repository.setCards(cards)
            repository.setIsXImage(true)
            repository.setIsPlayer1(!isPlayer1)
       	 }
    	}
	}
}
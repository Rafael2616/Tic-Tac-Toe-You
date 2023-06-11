package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun RestartGame( repository: GameRepository ) {

	val cardsString = repository.getCards()
	val cards = cardsString.map { it.toBoolean() }
	val isCardClickedString = repository.getIsCardClicked()
	val isCardClicked = isCardClickedString.map { it.toBoolean() }

    isCardClicked.all { false }
    repository.setIsCardClicked(isCardClicked) 
    cards.all { false }
    repository.setCards(cards)
    repository.setIsScoreUp(false)
    repository.setIs1Win(false)
    repository.setIsWinSoundPlaying(false)
    repository.setIsGotOldSoundPlaying(false)
    repository.setExpandButton(false)
    repository.setCoordinates(0f, 0f, 0f, 0f)
}
package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun RestartGame(repository: GameRepository) {

    val game = repository.getGameValues()
	val cardsString = repository.getCards()
	val cards = cardsString.map { it.toBoolean() }
	val isCardClickedString = repository.getIsCardClicked()
	val isCardClicked = isCardClickedString.map { it.toBoolean() }

    isCardClicked.all { false }
    repository.setIsCardClicked(isCardClicked) 
    cards.all { false }
    repository.setCards(cards)
	game.isScoreUp = false
    game.is1Win = false
    game.isWinSoundPlaying = false
    game.isGotOldSoundPlaying = false
    game.expandButton = false
    game.startX = 0f
    game.startY = 0f
    game.endX = 0f
	game.endY = 0f
    repository.setGameValues(game)
}
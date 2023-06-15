package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun CheckIfGotOld(repository: GameRepository) {
    
    val game = repository.getGameValues()
	val isCardClickedString = repository.getIsCardClicked()
	val isCardClicked = isCardClickedString.map { it.toBoolean() }
	val winner = game.winner

if ((isCardClicked.all { it }) && (!winner)) {
	game.gotOld = false
	repository.setGameValues(game)
    }
}
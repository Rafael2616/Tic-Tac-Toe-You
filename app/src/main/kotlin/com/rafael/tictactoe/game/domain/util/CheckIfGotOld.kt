package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun CheckIfGotOld(repository: GameRepository) {

	val isCardClickedString = repository.getIsCardClicked()
	val isCardClicked = isCardClickedString.map { it.toBoolean() }
	val winner = repository.getWinner()

if ((isCardClicked.all { it }) && (!winner)) {
	repository.setGotOld(false)
    }
}
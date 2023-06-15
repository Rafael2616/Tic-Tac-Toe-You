package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun RestartButtonLogic(repository: GameRepository) {

    val game = repository.getGameValues()
	val winner = game.winner
	val gotOld = game.gotOld
	val isCardClickedString = repository.getIsCardClicked()
	val isCardClicked = isCardClickedString.map { it.toBoolean() }

if ((isCardClicked.all {!it}) || (winner) || (gotOld)) {
    game.showRestartButton = false
    repository.setGameValues(game)
	}
    game.showRestartButton = true
    repository.setGameValues(game)
}
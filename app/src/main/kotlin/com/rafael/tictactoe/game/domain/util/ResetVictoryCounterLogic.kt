package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun ResetVictoryCounterLogic(repository: GameRepository) {

    val game = repository.getGameValues()
    game.player1Wins = 0
    game.player2Wins = 0
	repository.setGameValues(game)
}
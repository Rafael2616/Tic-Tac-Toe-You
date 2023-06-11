package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository


suspend fun ResetVictoryCounterLogic(repository: GameRepository) {

	repository.setPlayer1Wins(0)
	repository.setPlayer2Wins(0)
}
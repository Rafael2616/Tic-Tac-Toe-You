package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*


fun ResetVictoryCounterLogic(
repository: GameRepository
) = runBlocking {

repository.setPlayer1Wins(0)
repository.setPlayer2Wins(0)
}
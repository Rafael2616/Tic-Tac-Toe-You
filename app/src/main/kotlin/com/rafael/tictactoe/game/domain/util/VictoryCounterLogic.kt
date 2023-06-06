package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*


fun VictoryCounterLogic(
repository: GameRepository
) = runBlocking {

val isScoreUp = repository.getIsScoreUp()
val winner = repository.getWinner()
val is1Win = repository.getIs1Win()

if (!isScoreUp) {
    if (winner) {
        repository.setIsScoreUp(true)
      if (is1Win) {
           repository.incrementPlayer1Wins()
   } else {
           repository.incrementPlayer2Wins()
         }
      }
   }
}
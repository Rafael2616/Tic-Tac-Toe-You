package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*

/*
fun CheckIfGotOld(
isCardClicked: List<Boolean>,
repository: GameRepository
//winner: Boolean
) = runBlocking {

  val winner = repository.getWinner()

  if ((isCardClicked.all { it }) && (!winner)) {
  repository.setGotOld(false)
  }
   // // //
}
*/
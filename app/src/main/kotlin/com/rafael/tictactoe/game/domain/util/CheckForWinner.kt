package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*

/*
fun CheckForWinner(
repository: GameRepository,
isCardClicked: List<Boolean>,
cards: List<Boolean>,
//is1Win: Boolean
) = runBlocking {

val is1Win = repository.getIs1Win()
    val winningPositions = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(0, 4, 8),
        listOf(2, 4, 6)
   )
   for (positions in winningPositions) {
         if (positions.all { (isCardClicked.getOrNull(it) == true) &&
                     (cards.getOrNull(it) == false )}) {
         repository.setIs1Win(false)
         repository.setWinner(true)
       }
       else {
         if (positions.all { (isCardClicked.getOrNull(it) == true) && 
                    (cards.getOrNull(it) == true )}) {
         repository.setIs1Win(true)
         repository.setWinner(true)
         }
      }
   }
  // // //
}
*/
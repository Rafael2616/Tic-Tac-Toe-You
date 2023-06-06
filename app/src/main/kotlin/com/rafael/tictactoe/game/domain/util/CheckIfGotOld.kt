package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*


fun CheckIfGotOld(
repository: GameRepository
) = runBlocking {

val isCardClickedString = repository.getIsCardClicked()
val isCardClicked = isCardClickedString.map { it.toBoolean() }
val winner = repository.getWinner()

if ((isCardClicked.all { it }) && (!winner)) {
	repository.setGotOld(false)
    }
}
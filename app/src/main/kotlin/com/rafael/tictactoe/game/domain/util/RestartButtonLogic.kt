package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*


fun RestartButtonLogic(
repository: GameRepository
) = runBlocking {

val winner = repository.getWinner()
val gotOld = repository.getGotOld()
val isCardClickedString = repository.getIsCardClicked()
val isCardClicked = isCardClickedString.map { it.toBoolean() }

if ((isCardClicked.all {!it}) || (winner) || (gotOld)) {
    repository.setShowRestartButton(false)
	}
  repository.setShowRestartButton(true)
}
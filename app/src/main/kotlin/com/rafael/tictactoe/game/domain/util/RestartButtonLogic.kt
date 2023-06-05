package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*

/*
fun RestartButtonLogic(
repository: GameRepository,
isCardClicked: List<Boolean>,
/*winner: Boolean,
gotOld: Boolean*/ /*
) = runBlocking {


val winner = repository.getWinner()
val gotOld = repository.getGotOld()

if ((isCardClicked.all {!it}) || (winner) || (gotOld)) {
return false
}
return true
}
*/
package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import androidx.compose.runtime.*
import androidx.compose.ui.res.*
import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*

/*
@Composable
fun GameStateToastLogic(
repository: GameRepository,*/
/*winner: Boolean,
player1Name: String,
player2Name: String,
isPlayer1: Boolean,
gotOld: Boolean*//*
) = runBlocking {
  val genericPlayerName = R.string.player
  val gotOldString = R.string.got_old


val winner = repository.getWinner()
val player1Name = repository.getPlayer1Name()
val player2Name = repository.getPlayer2Name()
val isPlayer1 = repository.getIsPlayer1()
val gotOld = repository.getGotOld()

val playerName = if(!winner) {
if  (isPlayer1) player1Name else player2Name
}
else { if (!isPlayer1) player1Name else player2Name
}

if (gotOld) {
    return gotOldString
}
return if ((player1Name.isEmpty()) && (player2Name.isEmpty()))
 genericPlayerName
else if ((player1Name.isNotBlank()) && (player2Name.isNotBlank()))
 playerName
else if ((player1Name.isEmpty()) && (player2Name.isNotBlank()) && (isPlayer1))
 genericPlayerName
else if ((player1Name.isEmpty()) && (player2Name.isNotBlank()) && (!isPlayer1))
 player2Name
else if ((player1Name.isNotBlank()) && (player2Name.isEmpty()) && (isPlayer1))
 player1Name
else if ((player1Name.isNotBlank()) && (player2Name.isEmpty()) && (!isPlayer1))
 genericPlayerName
else ("")
}
*/
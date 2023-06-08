package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import androidx.compose.runtime.*
import androidx.compose.ui.res.*
import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*

/*
class DisplayPlayerNames {

@Composable
fun firstPlayerName(
repository: GameRepository
) = runBlocking {

val genericPlayerX = stringResource(R.string.player_x)
val genericPlayerO = stringResource(R.string.player_o)
val play = repository.getPlay()
val player1InToast = repository.getPlayer1InToast()
val isPlayer1 = repository.getIsPlayer1()
val isXFor1 = repository.getIsXFor1()
val player1Name = repository.getPlayer1Name()
val player2Name = repository.getPlayer2Name()

if(!play) repository.setPlayer1InToast(isPlayer1)
  
      if (isXFor1) {
            if (player1InToast) {
                if (player1Name.isEmpty()) {
                   repository.setCurrentPlayerName(genericPlayerX)
                } else {
                    repository.setCurrentPlayerName(player1Name)
                }
            } else {
                if (player2Name.isEmpty()) {
                    repository.setCurrentPlayerName(genericPlayerX)
                } else {
                    repository.setCurrentPlayerName(player2Name)
                }
            }
        } else {
            if (player1InToast) {
                if (player2Name.isEmpty()) {
                    repository.setCurrentPlayerName(genericPlayerX)
                } else {
                   repository.setCurrentPlayerName(player2Name)
                }
            } else {
                if (player1Name.isEmpty()) {
                   repository.setCurrentPlayerName(genericPlayerX)
                } else {
                   repository.setCurrentPlayerName(player1Name)
               }
          }
     }
}


@Composable
fun secondPlayerName(
repository: GameRepository
) = runBlocking {

val genericPlayerX = stringResource(R.string.player_x)
val genericPlayerO = stringResource(R.string.player_o)
val play = repository.getPlay()
val player1InToast = repository.getPlayer1InToast()
val isPlayer1 = repository.getIsPlayer1()
val isXFor1 = repository.getIsXFor1()
val player1Name = repository.getPlayer1Name()
val player2Name = repository.getPlayer2Name()

       return if (isXFor1) {
            if (player1InToast) {
                if (player2Name.isEmpty()) {
                    genericPlayerO
                } else {
                    player2Name
                }
            } else {
                if (player1Name.isEmpty()) {
                    genericPlayerO
                } else {
                    player1Name
                }
            }
        } else {
            if (player1InToast) {
                if (player1Name.isEmpty()) {
                    genericPlayerO
                } else {
                    player1Name
                }
            } else {
                if (player2Name.isEmpty()) {
                    genericPlayerO
                } else {
                    player2Name
               }
          }
     }
}

}
*/
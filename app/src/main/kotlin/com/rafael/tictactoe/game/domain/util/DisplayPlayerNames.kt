package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*


class DisplayPlayerNames {

fun firstPlayerName(
repository: GameRepository,
genericPlayerX: String,
genericPlayerO: String
) = runBlocking {

val play = repository.getPlay()
val player1InToast = repository.getPlayer1InToast()
val isPlayer1 = repository.getIsPlayer1()
val isXFor1 = repository.getIsXFor1()
val player1Name = repository.getPlayer1Name()
val player2Name = repository.getPlayer2Name()

if (isXFor1) {
    if (player1InToast) {
        if (player1Name.isEmpty()) {
            repository.setShowPlayer1Name(genericPlayerX)
  	      } else {
               repository.setShowPlayer1Name(player1Name)
            }
        } else {
            if (player2Name.isEmpty()) {
				repository.setShowPlayer1Name(genericPlayerX)
            } else {
                repository.setShowPlayer1Name(player2Name)
            }
        }
    } else {
        if (player1InToast) {
            if (player2Name.isEmpty()) {
                repository.setShowPlayer1Name(genericPlayerX)
            } else {
                repository.setShowPlayer1Name(player2Name)
            }
        } else {
            if (player1Name.isEmpty()) {
                repository.setShowPlayer1Name(genericPlayerX)
            } else {
                repository.setShowPlayer1Name(player1Name)
			}
		}
	}
}

fun secondPlayerName(
repository: GameRepository,
genericPlayerX: String,
genericPlayerO: String
) = runBlocking {

val play = repository.getPlay()
val player1InToast = repository.getPlayer1InToast()
val isPlayer1 = repository.getIsPlayer1()
val isXFor1 = repository.getIsXFor1()
val player1Name = repository.getPlayer1Name()
val player2Name = repository.getPlayer2Name()

if (isXFor1) {
	if (player1InToast) {
		if (player2Name.isEmpty()) {
			repository.setShowPlayer2Name(genericPlayerO)
			} else {
     	       repository.setShowPlayer2Name(player2Name)
            }
        } else {
            if (player1Name.isEmpty()) {
                repository.setShowPlayer2Name(genericPlayerO)
            } else {
                repository.setShowPlayer2Name(player1Name)
            }
        }
    } else {
        if (player1InToast) {
            if (player1Name.isEmpty()) {
                repository.setShowPlayer2Name(genericPlayerO)
    	    } else {
                repository.setShowPlayer2Name(player1Name)
            }
        } else {
            if (player2Name.isEmpty()) {
                repository.setShowPlayer2Name(genericPlayerO)
    	    } else {
                repository.setShowPlayer2Name(player2Name)
	        }
		}
	}
}

}
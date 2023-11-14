package com.rafael.tictactoe.game.domain.model

data class OnlineGameState(
    val player1Wins: Int = 0,
	val player2Wins: Int = 0,
	val card: List<List<Boolean>> = List(3){ List<Boolean>(3){false} },
	val isCardClicked: List<List<Boolean>> = List(3){ List<Boolean>(3){false} },
	val play: Boolean = false,
	val isXImage: Boolean = true,
	val is1Win: Boolean = false,
	val isXFor1: Boolean = true,
	val isPlayer1: Boolean = true,
	val hasWinner: Boolean = false,
	val gotOld: Boolean = false,
	val isGameFinished: Boolean = false,
)
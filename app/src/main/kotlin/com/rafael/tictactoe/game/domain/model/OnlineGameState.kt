package com.rafael.tictactoe.game.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.ui.graphics.vector.ImageVector
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel
import com.rafael.tictactoe.game.domain.model.GameMode

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
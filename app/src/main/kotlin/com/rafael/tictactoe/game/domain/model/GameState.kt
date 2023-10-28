package com.rafael.tictactoe.game.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.ui.graphics.vector.ImageVector
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel

data class GameState(
	val currentPlayerName: String = "",
    val player1Name: String = "",
	val player2Name: String = "",
    val player1Wins: Int = 0,
	val player2Wins: Int = 0,
	val card: List<List<Boolean>> = List(3){ List<Boolean>(3){false} },
	val isCardClicked: List<List<Boolean>> = List(3){ List<Boolean>(3){false} },
	val isTimerOn: Boolean = true,
    val showVictoryCounterReseter: Boolean = false,
	val play: Boolean = false,
	val isXImage: Boolean = true,
	val is1Win: Boolean = false,
	val isXFor1: Boolean = true,
	val isPlayer1: Boolean = true,
	val localPlayer1: Boolean = true,
	val isScoreUp: Boolean = false,
	val isExpandButtonShown: Boolean = false,
	val showRestartButton: Boolean = false,
	val isWinSoundPlaying: Boolean = false,
	val isGotOldSoundPlaying: Boolean = false,
	val winner: Boolean = false,
	val gotOld: Boolean = false,
	val isGameFinished: Boolean = false,
	val showPlayer1Name: String = "",
	val showPlayer2Name: String = "",
	val isCPU: Boolean = false,
	val isCPUMode: Boolean = false,
	val cpuDifficultyLevel: CPUDifficultyLevel = CPUDifficultyLevel.Easy,
	val isCPUPlayer1: Boolean = false,
	val isUsingEasyLevel: Boolean = false,
	val difficultyLevelName: Int = R.string.level_easy,
	val difficultyLevelIcon: ImageVector = Icons.Filled.SentimentSatisfied,
	val showExitGameDialog: Boolean = false,
	val winningPositionsIndices: List<Int> = emptyList(),
    val selectedDifficultyMode: Int = 0
)
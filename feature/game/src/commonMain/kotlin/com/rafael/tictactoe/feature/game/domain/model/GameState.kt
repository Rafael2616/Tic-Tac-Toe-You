package com.rafael.tictactoe.feature.game.domain.model

import com.rafael.tictactoe.core.domain.network.Error
import com.rafael.tictactoe.core.domain.network.Result
import com.rafael.tictactoe.core.navigation.GameMode
import com.rafael.tictactoe.feature.game.domain.repository.ErrorMessage

data class GameState(
    val currentPlayerName: String = "",
    val player1Name: String = "",
    val player2Name: String = "",
    val player1Wins: Int = 0,
    val player2Wins: Int = 0,
    val card: List<List<Boolean>> = List(3) { List(3) { false } },
    val isCardClicked: List<List<Boolean>> = List(3) { List(3) { false } },
    val isTimerOn: Boolean = false,
    val showVictoryCounterReset: Boolean = false,
    val play: Boolean = false,
    val isXImage: Boolean = true,
    val is1Win: Boolean = false,
    val isXFor1: Boolean = true,
    val isPlayer1: Boolean = true,
    val localPlayer1: Boolean = true,
    val isExpandButtonShown: Boolean = false,
    val showRestartButton: Boolean = false,
    val isWinSoundPlaying: Boolean = false,
    val isGotOldSoundPlaying: Boolean = false,
    val hasWinner: Boolean = false,
    val gotOld: Boolean = false,
    val isGameFinished: Boolean = false,
    val player1NameOnCounter: String = "",
    val player2NameOnCounter: String = "",
    val isCPU: Boolean = false,
    val gameMode: GameMode = GameMode.PvP,
    val isCPUPlayer1: Boolean = false,
    val isUsingEasyLevel: Boolean = false,
    val showExitGameDialog: Boolean = false,
    val winningPositionsIndices: List<Int> = emptyList(),
    val selectedDifficultyLevel: Level = Level.getLevels(true)[1],
    val hasLastWinningPlayer: Boolean = false,
    val requestState: Pair<Result<Any, Error>, ErrorMessage>? = null,
    val matchID: Int = 0,
    val secondPlayer: Player? = null,
    val isOnlinePlayerTurn: Boolean = false,
    val isYouPlayer1: Boolean = true,
    val showGetPrefsDialog: Boolean = true,
    val showSearchForPlayersSurface: Boolean = false,
)

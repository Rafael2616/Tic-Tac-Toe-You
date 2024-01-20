package com.rafael.tictactoe.game.domain.toast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.CurrentPlayerName
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState
import kotlinx.coroutines.delay

@Composable
fun ToastNameHandler(
    state: GameState,
    onEvent: (GameEvent) -> Unit,
) {
    val hasWinner = state.hasWinner
    val gotOld = state.gotOld
    val play = state.play
    val player1Name = state.player1Name
    val player2Name = state.player2Name
    val isCPUPlayer1 = state.isCPUPlayer1
    val isPlayer1 = state.isPlayer1
    val is1Win = state.is1Win
    val isTimerOn = state.isTimerOn
    val isGameFinished = state.isGameFinished
    val isCPUMode = state.gameMode == GameMode.VsCPU
    val localPlayer1 = state.localPlayer1
    var timer by rememberSaveable { mutableIntStateOf(3) }
    val emptyPlayerName = stringResource(R.string.player)
    val cpuName = stringResource(R.string.cpu)
    var currentGamePhase: CurrentPlayerName = CurrentPlayerName.NameForSetupPhase
    val actualOnEvent by rememberUpdatedState(onEvent)

    // Update game phase
    if (!play && !isCPUMode) {
        currentGamePhase = CurrentPlayerName.NameForSetupPhase
    } else if (!play && isCPUMode) {
        currentGamePhase = CurrentPlayerName.NameForSetupPhaseForCPUMode
    } else if (play && isTimerOn) {
        currentGamePhase = CurrentPlayerName.CounterAfterGameStarts
    } else if (play && !isTimerOn && !isGameFinished) {
        currentGamePhase = CurrentPlayerName.NameForGameInProgress
    } else if (hasWinner) {
        currentGamePhase = CurrentPlayerName.NameWhenThereWinner
    } else if (gotOld) {
        currentGamePhase = CurrentPlayerName.NameWhenGotOld
    }

    when (currentGamePhase) {
        CurrentPlayerName.NameForSetupPhase -> {
            if (localPlayer1) {
                if (player1Name.isEmpty()) {
                    onEvent(GameEvent.UpdateCurrentPlayerName(emptyPlayerName))
                } else {
                    onEvent(GameEvent.UpdateCurrentPlayerName(player1Name))
                }
            } else {
                if (player2Name.isEmpty()) {
                    onEvent(GameEvent.UpdateCurrentPlayerName(emptyPlayerName))
                } else {
                    onEvent(GameEvent.UpdateCurrentPlayerName(player2Name))
                }
            }
        }
        CurrentPlayerName.NameForSetupPhaseForCPUMode -> {
            if (isCPUMode) {
                if (isCPUPlayer1) {
                    onEvent(GameEvent.UpdatePlayer1Name(cpuName))
                    if (player2Name == cpuName) {
                        onEvent(GameEvent.UpdatePlayer2Name(player1Name))
                    }
                } else {
                    onEvent(GameEvent.UpdatePlayer2Name(cpuName))
                    if (player1Name == cpuName) {
                        onEvent(GameEvent.UpdatePlayer1Name(player2Name))
                    }
                }
            }
            if (localPlayer1) {
                if (player1Name.isEmpty()) {
                    onEvent(GameEvent.UpdateCurrentPlayerName(emptyPlayerName))
                } else {
                    onEvent(GameEvent.UpdateCurrentPlayerName(player1Name))
                }
            } else {
                if (player2Name.isEmpty()) {
                    onEvent(GameEvent.UpdateCurrentPlayerName(emptyPlayerName))
                } else {
                    onEvent(GameEvent.UpdateCurrentPlayerName(player2Name))
                }
            }
        }
        CurrentPlayerName.CounterAfterGameStarts -> {
            LaunchedEffect(currentGamePhase) {
                while (isTimerOn) {
                    if (timer > 0) {
                        actualOnEvent(GameEvent.UpdateCurrentPlayerName(timer.toString()))
                    }
                    delay(500L)
                    timer--
                    if (timer == 0) {
                        actualOnEvent(GameEvent.IsTimerOn(false))
                    }
                }
            }
        }
        CurrentPlayerName.NameForGameInProgress -> {
            if (isPlayer1) {
                if (player1Name.isEmpty()) {
                    onEvent(GameEvent.UpdateCurrentPlayerName(emptyPlayerName))
                    onEvent(GameEvent.UpdatePlayer1Name(emptyPlayerName))
                } else {
                    onEvent(GameEvent.UpdateCurrentPlayerName(player1Name))
                }
            } else {
                if (player2Name.isEmpty()) {
                    onEvent(GameEvent.UpdateCurrentPlayerName(emptyPlayerName))
                    onEvent(GameEvent.UpdatePlayer2Name(emptyPlayerName))
                } else {
                    onEvent(GameEvent.UpdateCurrentPlayerName(player2Name))
                }
            }
        }
        CurrentPlayerName.NameWhenThereWinner -> {
            if (is1Win) {
                onEvent(GameEvent.UpdateCurrentPlayerName(player1Name))
            } else {
                onEvent(GameEvent.UpdateCurrentPlayerName(player2Name))
            }
        }
        CurrentPlayerName.NameWhenGotOld -> {
            onEvent(GameEvent.UpdateCurrentPlayerName(stringResource(R.string.got_old)))
        }
    }
}

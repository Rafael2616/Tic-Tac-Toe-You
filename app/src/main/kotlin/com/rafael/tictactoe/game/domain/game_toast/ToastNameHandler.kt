package com.rafael.tictactoe.game.domain.game_toast

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.CurrentPlayerName
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import kotlinx.coroutines.delay

@Composable
fun ToastNameHandler(state: GameState) {
    val vm = hiltViewModel<GameViewModel>()
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
    val isCPUMode = state.isCPUMode
    val localPlayer1 = state.localPlayer1
    var timer by rememberSaveable { mutableIntStateOf(3) }
    val emptyPlayerName = stringResource(R.string.player)
    val cpuName = stringResource(R.string.cpu)
    var currentGamePhase: CurrentPlayerName = CurrentPlayerName.NameForSetupPhase

    //Update game phase
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
                    vm.updateCurrentPlayerName(emptyPlayerName)
               } else {
                    vm.updateCurrentPlayerName(player1Name)
               }         
            } else {
                if (player2Name.isEmpty()) {
                    vm.updateCurrentPlayerName(emptyPlayerName)
                } else {
                    vm.updateCurrentPlayerName(player2Name)
                }
            }
        }
        CurrentPlayerName.NameForSetupPhaseForCPUMode -> {
            if (isCPUMode) {
                if (isCPUPlayer1) {
                    vm.updatePlayer1Name(cpuName)
                    if (player2Name == cpuName) {
                        vm.updatePlayer2Name(player1Name)
                    }
                } else {
                    vm.updatePlayer2Name(cpuName)
                    if (player1Name == cpuName) {
                        vm.updatePlayer1Name(player2Name)
                    }
                }
            }
            if (localPlayer1) {
                if (player1Name.isEmpty()) {
                    vm.updateCurrentPlayerName(emptyPlayerName)
               } else {
                    vm.updateCurrentPlayerName(player1Name)
               }         
            } else {
                if (player2Name.isEmpty()) {
                    vm.updateCurrentPlayerName(emptyPlayerName)
                } else {
                    vm.updateCurrentPlayerName(player2Name)
                }
            }
        }
        CurrentPlayerName.CounterAfterGameStarts -> {
            LaunchedEffect(currentGamePhase) {
                while (isTimerOn) {
                    if (timer > 0) {
                        vm.updateCurrentPlayerName(timer.toString())
                    }
                    delay(500L)
                    timer --
                    if (timer == 0) {
                        vm.updateIsTimerOn(false)
                    }
                }
            }
        }
        CurrentPlayerName.NameForGameInProgress -> {
            if (isPlayer1) {
                if (player1Name.isEmpty()) {
                    vm.updateCurrentPlayerName(emptyPlayerName)
                    vm.updatePlayer1Name(emptyPlayerName)
                } else {
                    vm.updateCurrentPlayerName(player1Name)
                }
            } else {
                if (player2Name.isEmpty()) {
                    vm.updateCurrentPlayerName(emptyPlayerName)
                    vm.updatePlayer2Name(emptyPlayerName)
                } else {
                    vm.updateCurrentPlayerName(player2Name)
                }
            }
        }
        CurrentPlayerName.NameWhenThereWinner -> {
            if (is1Win) {
                vm.updateCurrentPlayerName(player1Name)
            } else {
                vm.updateCurrentPlayerName(player2Name)
            }
        }
        CurrentPlayerName.NameWhenGotOld -> {
    		vm.updateCurrentPlayerName(stringResource(R.string.got_old))
        }
    }
}
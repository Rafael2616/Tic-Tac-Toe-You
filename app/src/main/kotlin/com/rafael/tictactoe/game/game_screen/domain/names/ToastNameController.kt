package com.rafael.tictactoe.game.domain.names

import android.content.Context
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.CurrentPlayerName
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ToastNameController {

    suspend fun currentToastName(
        state: GameState,
        context: Context,
    ): String? {
        var currentGamePhase = getCurrentGamePhase(state)
        return when (currentGamePhase) {
            CurrentPlayerName.NameForSetupPhase -> currentNameForSetupPhase(state, context)
            CurrentPlayerName.NameForSetupPhaseForCPUMode -> currentNameForSetupPhaseForCPUMode(state, context)
            CurrentPlayerName.CounterAfterGameStarts -> null // counterAfterGameStarts(state)
            CurrentPlayerName.NameForGameInProgress -> currentNameForGameInProgress(state, context)
            CurrentPlayerName.NameWhenThereWinner -> currentNameWhenThereWinner(state)
            CurrentPlayerName.NameWhenGotOld -> currentNameWhenGotOld(context)
        }
    }

    fun currentNameForSetupPhase(state: GameState, context: Context): String {
        var currentPlayerName: String
        val emptyPlayerName = context.getString(R.string.player)

        if (state.localPlayer1) {
            if (state.player1Name.isEmpty()) {
                currentPlayerName = emptyPlayerName
            } else {
                currentPlayerName = state.player1Name
            }
        } else {
            if (state.player2Name.isEmpty()) {
                currentPlayerName = emptyPlayerName
            } else {
                currentPlayerName = state.player2Name
            }
        }
        return currentPlayerName
    }

    fun currentNameForSetupPhaseForCPUMode(state: GameState, context: Context): String {
        var currentPlayerName: String
        val emptyPlayerName = context.getString(R.string.player)

        if (state.localPlayer1) {
            if (state.player1Name.isEmpty()) {
                currentPlayerName = emptyPlayerName
            } else {
                currentPlayerName = state.player1Name
            }
        } else {
            if (state.player2Name.isEmpty()) {
                currentPlayerName = emptyPlayerName
            } else {
                currentPlayerName = state.player2Name
            }
        }
        return currentPlayerName
    }

    fun currentNameForGameInProgress(state: GameState, context: Context): String {
        var currentPlayerName: String
        val emptyPlayerName = context.getString(R.string.player)

        if (state.isPlayer1) {
            if (state.player1Name.isEmpty()) {
                currentPlayerName = emptyPlayerName
            } else {
                currentPlayerName = state.player1Name
            }
        } else {
            if (state.player2Name.isEmpty()) {
                currentPlayerName = emptyPlayerName
            } else {
                currentPlayerName = state.player2Name
            }
        }
        return currentPlayerName
    }

    fun currentNameWhenGotOld(context: Context): String {
        return context.getString(R.string.got_old)
    }

    fun currentNameWhenThereWinner(state: GameState): String {
        return if (state.is1Win) {
            state.player1Name
        } else {
            state.player2Name
        }
    }

    suspend fun counterAfterGameStarts(state: GameState): Flow<Pair<String, Boolean>> = flow {
        var timer = 3
        while (state.isTimerOn) {
            if (timer > 0) {
                emit(Pair(timer.toString(), true))
            }
            delay(500L)
            timer--
            if (timer == 0) {
                emit(Pair(timer.toString(), false))
                break
            }
        }
    }

    fun getCurrentGamePhase(state: GameState): CurrentPlayerName {
        val play = state.play
        val hasWinner = state.hasWinner
        val gotOld = state.gotOld
        val isTimerOn = state.isTimerOn
        val isGameFinished = state.isGameFinished
        val isCPUMode = state.gameMode == GameMode.VsCPU

        return when {
            !play && !isCPUMode -> CurrentPlayerName.NameForSetupPhase
            !play && isCPUMode -> CurrentPlayerName.NameForSetupPhaseForCPUMode
            play && isTimerOn -> CurrentPlayerName.CounterAfterGameStarts
            play && !isTimerOn && !isGameFinished -> CurrentPlayerName.NameForGameInProgress
            hasWinner && !gotOld -> CurrentPlayerName.NameWhenThereWinner
            else -> CurrentPlayerName.NameWhenGotOld
        }
    }
}

package com.rafael.tictactoe.feature.game.domain.names

import android.content.Context
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.game.domain.model.CurrentPlayerName
import com.rafael.tictactoe.feature.game.domain.model.GameState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ToastNameController {

    suspend fun currentToastName(
        state: GameState,
        context: Context,
    ): String? {
        var currentGamePhase = getCurrentGamePhase(state)
        return when (currentGamePhase) {
            CurrentPlayerName.NameForSetupPhase -> currentNameForSetupPhase(state, context)
            CurrentPlayerName.CounterAfterGameStarts -> null // counterAfterGameStarts(state)
            CurrentPlayerName.NameForGameInProgress -> currentNameForGameInProgress(state, context)
            CurrentPlayerName.NameWhenThereWinner -> currentNameWhenThereWinner(state)
            CurrentPlayerName.NameWhenGotOld -> currentNameWhenGotOld(context)
        }
    }

    private fun currentNameForSetupPhase(state: GameState, context: Context): String {
        val emptyPlayerName = context.getString(R.string.player)
        return if (state.localPlayer1 && state.player1Name.isNotEmpty()) {
            state.player1Name
        } else if (!state.localPlayer1 && state.player2Name.isNotEmpty()) {
            state.player2Name
        } else {
            emptyPlayerName
        }
    }

    private fun currentNameForGameInProgress(state: GameState, context: Context): String {
        val emptyPlayerName = context.getString(R.string.player)
        return if (state.isPlayer1) {
            if (state.player1Name.isEmpty()) emptyPlayerName else state.player1Name
        } else {
            if (state.player2Name.isEmpty()) emptyPlayerName else state.player2Name
        }
    }

    private fun currentNameWhenGotOld(context: Context): String {
        return context.getString(R.string.got_old)
    }

    private fun currentNameWhenThereWinner(state: GameState): String {
        return if (state.is1Win) {
            state.player1Name
        } else {
            state.player2Name
        }
    }

    private suspend fun counterAfterGameStarts(state: GameState): Flow<Pair<String, Boolean>> = flow {
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

    private fun getCurrentGamePhase(state: GameState): CurrentPlayerName {
        val play = state.play
        val hasWinner = state.hasWinner
        val gotOld = state.gotOld
        val isTimerOn = state.isTimerOn
        val isGameFinished = state.isGameFinished

        return when {
            !play -> CurrentPlayerName.NameForSetupPhase
            play && isTimerOn -> CurrentPlayerName.CounterAfterGameStarts
            play && !isTimerOn && !isGameFinished -> CurrentPlayerName.NameForGameInProgress
            hasWinner && !gotOld -> CurrentPlayerName.NameWhenThereWinner
            else -> CurrentPlayerName.NameWhenGotOld
        }
    }
}

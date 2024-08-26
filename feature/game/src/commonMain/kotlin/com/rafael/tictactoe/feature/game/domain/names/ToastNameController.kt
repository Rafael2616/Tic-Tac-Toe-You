package com.rafael.tictactoe.feature.game.domain.names

import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.got_old
import com.rafael.tictactoe.core.resources.player
import com.rafael.tictactoe.feature.game.domain.model.CurrentPlayerName
import com.rafael.tictactoe.feature.game.domain.model.GameState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.compose.resources.getString

internal class ToastNameController {

    suspend fun currentToastName(
        state: GameState,
    ): String? {
        val currentGamePhase = getCurrentGamePhase(state)
        return when (currentGamePhase) {
            CurrentPlayerName.NameForSetupPhase -> currentNameForSetupPhase(state)
            CurrentPlayerName.CounterAfterGameStarts -> null // counterAfterGameStarts(state)
            CurrentPlayerName.NameForGameInProgress -> currentNameForGameInProgress(state)
            CurrentPlayerName.NameWhenThereWinner -> currentNameWhenThereWinner(state)
            CurrentPlayerName.NameWhenGotOld -> currentNameWhenGotOld()
        }
    }

    private suspend fun currentNameForSetupPhase(state: GameState): String {
        val emptyPlayerName = getString(Res.string.player)
        return if (state.localPlayer1 && state.player1Name.isNotEmpty()) {
            state.player1Name
        } else if (!state.localPlayer1 && state.player2Name.isNotEmpty()) {
            state.player2Name
        } else {
            emptyPlayerName
        }
    }

    private suspend fun currentNameForGameInProgress(state: GameState): String {
        val emptyPlayerName = getString(Res.string.player)
        return if (state.isPlayer1) {
            state.player1Name.ifEmpty { emptyPlayerName }
        } else {
            state.player2Name.ifEmpty { emptyPlayerName }
        }
    }

    private suspend fun currentNameWhenGotOld(): String = getString(Res.string.got_old)

    private fun currentNameWhenThereWinner(state: GameState): String = if (state.is1Win) {
        state.player1Name
    } else {
        state.player2Name
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

        return when {
            !play -> CurrentPlayerName.NameForSetupPhase
            play && isTimerOn -> CurrentPlayerName.CounterAfterGameStarts
            play && !isTimerOn && !isGameFinished -> CurrentPlayerName.NameForGameInProgress
            hasWinner && !gotOld -> CurrentPlayerName.NameWhenThereWinner
            else -> CurrentPlayerName.NameWhenGotOld
        }
    }
}

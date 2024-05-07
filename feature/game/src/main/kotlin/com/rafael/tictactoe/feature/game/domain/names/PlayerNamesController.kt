package com.rafael.tictactoe.game.domain.names

import android.content.Context
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.CurrentPlayerName
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState

class PlayerNamesController {

    fun currentPlayerNames(
        state: GameState,
        context: Context,
    ): Pair<String, String> {
        var currentGamePhase = ToastNameController().getCurrentGamePhase(state)

        return when (currentGamePhase) {
            CurrentPlayerName.NameForSetupPhase -> playerNamesForSetupPhaseForCPUMode(state, context)
            CurrentPlayerName.NameForGameInProgress -> playerNamesForGameInProgress(state, context)
            else -> return Pair(state.player1Name, state.player2Name)
        }
    }

    fun playerNamesForSetupPhaseForCPUMode(state: GameState, context: Context): Pair<String, String> {
        var player1Name = ""
        var player2Name = ""
        val cpuName = context.getString(R.string.cpu)
        val isCPUMode = state.gameMode == GameMode.VsCPU

        if (isCPUMode) {
            if (state.isCPUPlayer1) {
                player1Name = cpuName
                if (state.player2Name == cpuName) {
                    player2Name = state.player1Name
                }
            } else {
                player2Name = cpuName
                if (state.player1Name == cpuName) {
                    player1Name = state.player2Name
                }
            }
        } else {
            player1Name = state.player1Name
            player2Name = state.player2Name
        }
        return Pair(player1Name, player2Name)
    }

    fun playerNamesForGameInProgress(state: GameState, context: Context): Pair<String, String> {
        val emptyPlayerName = context.getString(R.string.player)
        val player1Name = if (state.player1Name.isEmpty()) emptyPlayerName else state.player1Name
        val player2Name = if (state.player2Name.isEmpty()) emptyPlayerName else state.player2Name
        return Pair(player1Name, player2Name)
    }
}

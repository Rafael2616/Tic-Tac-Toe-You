package com.rafael.tictactoe.feature.game.domain.counter

import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.cpu
import com.rafael.tictactoe.core.resources.player
import com.rafael.tictactoe.core.resources.player_o
import com.rafael.tictactoe.core.resources.player_x
import com.rafael.tictactoe.feature.game.domain.checkers.Checkers
import com.rafael.tictactoe.feature.game.domain.model.GameMode
import com.rafael.tictactoe.feature.game.domain.model.GameState
import org.jetbrains.compose.resources.getString

internal class VictoryCounter {

    fun updateVictoryCounter(state: GameState): Pair<Int, Int> {
        return if (state.is1Win) {
            Pair(state.player1Wins + 1, state.player2Wins)
        } else {
            Pair(state.player1Wins, state.player2Wins + 1)
        }
    }

    fun shouldShowVictoryCounterReset(state: GameState): Boolean {
        val isCounterReset = state.player1Wins + state.player2Wins
        val hasWinner = Checkers().checkWinner(state).first

        return isCounterReset > 0 || hasWinner
    }

    suspend fun setFirstName(state: GameState): String {
        val isXFor1 = state.isXFor1
        val player1Name = state.player1Name
        val player2Name = state.player2Name
        val genericPlayerX = getString(Res.string.player_x)
        val genericPlayerO = getString(Res.string.player_o)
        val emptyPlayerName = getString(Res.string.player)
        val cpuName = getString(Res.string.cpu)
        val isCPUMode = state.gameMode == GameMode.VsCPU

        // Determine the name for the first player
        val firstPlayerName = when {
            isCPUMode && state.isCPUPlayer1 -> cpuName
            !state.play -> {
                if (state.localPlayer1) {
                    player1Name.ifEmpty {
                        if (isXFor1) genericPlayerX else genericPlayerO
                    }
                } else {
                    player2Name.ifEmpty {
                        if (isXFor1) genericPlayerX else genericPlayerO
                    }
                }
            }
            else -> {
                if (player1Name.isEmpty() || player1Name == emptyPlayerName) {
                    if (isXFor1) genericPlayerX else genericPlayerO
                } else {
                    player1Name
                }
            }
        }

        return firstPlayerName
    }

    suspend fun setSecondName(state: GameState): String {
        val isXFor1 = state.isXFor1
        val player1Name = state.player1Name
        val player2Name = state.player2Name
        val genericPlayerX = getString(Res.string.player_x)
        val genericPlayerO = getString(Res.string.player_o)
        val emptyPlayerName = getString(Res.string.player)
        val cpuName = getString(Res.string.cpu)
        val isCPUMode = state.gameMode == GameMode.VsCPU

        // Determine the name for the second player
        val secondPlayerName = when {
            isCPUMode && !state.isCPUPlayer1 -> cpuName
            !state.play -> {
                if (state.localPlayer1) {
                    player2Name.ifEmpty {
                        if (isXFor1) genericPlayerO else genericPlayerX
                    }
                } else {
                    player1Name.ifEmpty {
                        if (isXFor1) genericPlayerO else genericPlayerX
                    }
                }
            }
            else -> {
                if (player2Name.isEmpty() || player2Name == emptyPlayerName) {
                    if (isXFor1) genericPlayerO else genericPlayerX
                } else {
                    player2Name
                }
            }
        }

        return secondPlayerName
    }
}

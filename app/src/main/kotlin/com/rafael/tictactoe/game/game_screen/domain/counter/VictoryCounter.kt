package com.rafael.tictactoe.game.domain.counter

import android.content.Context
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.checkers.Checkers
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState

class VictoryCounter {
    fun updateVictoryCounter(state: GameState): Pair<Int, Int> {
        var player1Wins = state.player1Wins
        var player2Wins = state.player2Wins

        if (state.is1Win) {
            player1Wins = player1Wins + 1
        } else {
            player2Wins = player2Wins + 1
        }
        return Pair(player1Wins, player2Wins)
    }

    fun shouldShowVictoryCounterReseter(state: GameState): Boolean {
        val isCounterReset = state.player1Wins + state.player2Wins
        val hasWinner = Checkers().checkWinner(state).first

        if (isCounterReset > 0 || hasWinner) {
            return true
        }
        return false
    }

    fun setFirstName(
        state: GameState,
        context: Context,
    ): String {
        val isXFor1 = state.isXFor1
        val player1Name = state.player1Name
        val player2Name = state.player2Name
        val genericPlayerX = context.getString(R.string.player_x)
        val genericPlayerO = context.getString(R.string.player_o)
        val gameMode = state.gameMode
        val isCPUPlayer1 = state.isCPUPlayer1
        val localPlayer1 = state.localPlayer1
        val play = state.play
        val emptyPlayerName = context.getString(R.string.player)
        val cpuName = context.getString(R.string.cpu)
        var player1NameOnCounter = state.player1NameOnCounter

        // Apply "CPU" name for first player name when needed in CPU Mode
        if (gameMode == GameMode.VsCPU) {
            if (isCPUPlayer1) {
                player1NameOnCounter = cpuName
            } else {
                player1NameOnCounter = ""
            }
        }

        // Handle first player name when game is in setup fase/not started yet
        if (!play) {
            if (localPlayer1) {
                if (player1Name.isEmpty()) {
                    if (isXFor1) {
                        player1NameOnCounter = genericPlayerX
                    } else {
                        player1NameOnCounter = genericPlayerO
                    }
                } else {
                    player1NameOnCounter = player1Name
                }
            } else {
                if (player2Name.isEmpty()) {
                    if (isXFor1) {
                        player1NameOnCounter = genericPlayerX
                    } else {
                        player1NameOnCounter = genericPlayerO
                    }
                } else {
                    player1NameOnCounter = player2Name
                }
            }
        }

        // Handle first player name when game starts
        if (play) {
            if (player1Name.isEmpty() || player1Name == emptyPlayerName) {
                if (isXFor1) {
                    player1NameOnCounter = genericPlayerX
                } else {
                    player1NameOnCounter = genericPlayerO
                }
            } else {
                player1NameOnCounter = player1Name
            }
        }
        return player1NameOnCounter
    }

    fun setSecondName(
        state: GameState,
        context: Context,
    ): String {
        val isXFor1 = state.isXFor1
        val player1Name = state.player1Name
        val player2Name = state.player2Name
        val genericPlayerO = context.getString(R.string.player_o)
        val gameMode = state.gameMode
        val isCPUPlayer1 = state.isCPUPlayer1
        val genericPlayerX = context.getString(R.string.player_x)
        val localPlayer1 = state.localPlayer1
        val play = state.play
        val emptyPlayerName = context.getString(R.string.player)
        val cpuName = context.getString(R.string.cpu)
        var player2NameOnCounter = state.player2NameOnCounter

        // Apply "CPU" name for second player name when needed in CPU Mode
        if (gameMode == GameMode.VsCPU) {
            if (!isCPUPlayer1) {
                player2NameOnCounter = cpuName
            } else {
                player2NameOnCounter = ""
            }
        }

        // Handle second player name when game is in setup fase/not started yet
        if (!play) {
            if (localPlayer1) {
                if (player2Name.isEmpty()) {
                    if (isXFor1) {
                        player2NameOnCounter = genericPlayerO
                    } else {
                        player2NameOnCounter = genericPlayerX
                    }
                } else {
                    player2NameOnCounter = player2Name
                }
            } else {
                if (player1Name.isEmpty()) {
                    if (isXFor1) {
                        player2NameOnCounter = genericPlayerO
                    } else {
                        player2NameOnCounter = genericPlayerX
                    }
                } else {
                    player2NameOnCounter = player1Name
                }
            }
        }

        // Handle second player name when game starts
        if (play) {
            if (player2Name.isEmpty() || player2Name == emptyPlayerName) {
                if (isXFor1) {
                    player2NameOnCounter = genericPlayerO
                } else {
                    player2NameOnCounter = genericPlayerX
                }
            } else {
                player2NameOnCounter = player2Name
            }
        }
        return player2NameOnCounter
    }
}

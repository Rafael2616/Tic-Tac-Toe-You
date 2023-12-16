package com.rafael.tictactoe.game.domain.victoryCounter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState

class PlayerNamesOnCounterHandler() {

    @Composable
    fun FirstPlayerName(
        state: GameState,
        onEvent: (GameEvent) -> Unit,
    ) {
        val isXFor1 = state.isXFor1
        val player1Name = state.player1Name
        val player2Name = state.player2Name
        val genericPlayerX = stringResource(R.string.player_x)
        val genericPlayerO = stringResource(R.string.player_o)
        val gameMode = state.gameMode
        val isCPUPlayer1 = state.isCPUPlayer1
        val localPlayer1 = state.localPlayer1
        val play = state.play
        val emptyPlayerName = stringResource(R.string.player)
        val cpuName = stringResource(R.string.cpu)

        // Apply "CPU" name for first player name when needed in CPU Mode
        if (gameMode == GameMode.VsCPU) {
            if (isCPUPlayer1) {
                onEvent(GameEvent.UpdatePlayer1NameOnCounter(cpuName))
            } else {
                onEvent(GameEvent.UpdatePlayer1NameOnCounter(""))
            }
        }

        // Handle first player name when game is in setup fase/not started yet
        if (!play) {
            if (localPlayer1) {
                if (player1Name.isEmpty()) {
                    if (isXFor1) {
                        onEvent(GameEvent.UpdatePlayer1NameOnCounter(genericPlayerX))
                    } else {
                        onEvent(GameEvent.UpdatePlayer1NameOnCounter(genericPlayerO))
                    }
                } else {
                    onEvent(GameEvent.UpdatePlayer1NameOnCounter(player1Name))
                }
            } else {
                if (player2Name.isEmpty()) {
                    if (isXFor1) {
                        onEvent(GameEvent.UpdatePlayer1NameOnCounter(genericPlayerX))
                    } else {
                        onEvent(GameEvent.UpdatePlayer1NameOnCounter(genericPlayerO))
                    }
                } else {
                    onEvent(GameEvent.UpdatePlayer1NameOnCounter(player2Name))
                }
            }
        }

        // Handle first player name when game is ongoing or finished
        if (play) {
            if (player1Name.isEmpty() || player1Name == emptyPlayerName) {
                if (isXFor1) {
                    onEvent(GameEvent.UpdatePlayer1NameOnCounter(genericPlayerX))
                } else {
                    onEvent(GameEvent.UpdatePlayer1NameOnCounter(genericPlayerO))
                }
            } else {
                onEvent(GameEvent.UpdatePlayer1NameOnCounter(player1Name))
            }
        }
    }

    @Composable
    fun SecondPlayerName(
        state: GameState,
        onEvent: (GameEvent) -> Unit,
    ) {
        val isXFor1 = state.isXFor1
        val player1Name = state.player1Name
        val player2Name = state.player2Name
        val genericPlayerO = stringResource(R.string.player_o)
        val gameMode = state.gameMode
        val isCPUPlayer1 = state.isCPUPlayer1
        val genericPlayerX = stringResource(R.string.player_x)
        val localPlayer1 = state.localPlayer1
        val play = state.play
        val emptyPlayerName = stringResource(R.string.player)
        val cpuName = stringResource(R.string.cpu)

        // Apply "CPU" name for second player name when needed in CPU Mode
        if (gameMode == GameMode.VsCPU) {
            if (!isCPUPlayer1) {
                onEvent(GameEvent.UpdatePlayer2NameOnCounter(cpuName))
            } else {
                onEvent(GameEvent.UpdatePlayer2NameOnCounter(""))
            }
        }

        // Handle second player name when game is in setup fase/not started yet
        if (!play) {
            if (localPlayer1) {
                if (player2Name.isEmpty()) {
                    if (isXFor1) {
                        onEvent(GameEvent.UpdatePlayer2NameOnCounter(genericPlayerO))
                    } else {
                        onEvent(GameEvent.UpdatePlayer2NameOnCounter(genericPlayerX))
                    }
                } else {
                    onEvent(GameEvent.UpdatePlayer2NameOnCounter(player2Name))
                }
            } else {
                if (player1Name.isEmpty()) {
                    if (isXFor1) {
                        onEvent(GameEvent.UpdatePlayer2NameOnCounter(genericPlayerO))
                    } else {
                        onEvent(GameEvent.UpdatePlayer2NameOnCounter(genericPlayerX))
                    }
                } else {
                    onEvent(GameEvent.UpdatePlayer2NameOnCounter(player1Name))
                }
            }
        }

        // Handle second player name when game is ongoing or finished
        if (play) {
            if (player2Name.isEmpty() || player2Name == emptyPlayerName) {
                if (isXFor1) {
                    onEvent(GameEvent.UpdatePlayer2NameOnCounter(genericPlayerO))
                } else {
                    onEvent(GameEvent.UpdatePlayer2NameOnCounter(genericPlayerX))
                }
            } else {
                onEvent(GameEvent.UpdatePlayer2NameOnCounter(player2Name))
            }
        }
    }
}

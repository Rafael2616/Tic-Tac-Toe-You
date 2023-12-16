package com.rafael.tictactoe.game.domain.victoryCounter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.gameScreen.viewmodels.GameViewModel

class PlayerNamesOnCounterHandler() {

    @Composable
    fun FirstPlayerName(
        state: GameState,
        vm: GameViewModel = hiltViewModel(),
    ) {
        val context = LocalContext.current
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

        // Apply "CPU" name for first player name when needed in CPU Mode
        if (gameMode == GameMode.VsCPU) {
            if (isCPUPlayer1) {
                vm.updatePlayer1NameOnCounter(cpuName)
            } else {
                vm.updatePlayer1NameOnCounter("")
            }
        }

        // Handle first player name when game is in setup fase/not started yet
        if (!play) {
            if (localPlayer1) {
                if (player1Name.isEmpty()) {
                    if (isXFor1) {
                        vm.updatePlayer1NameOnCounter(genericPlayerX)
                    } else {
                        vm.updatePlayer1NameOnCounter(genericPlayerO)
                    }
                } else {
                    vm.updatePlayer1NameOnCounter(player1Name)
                }
            } else {
                if (player2Name.isEmpty()) {
                    if (isXFor1) {
                        vm.updatePlayer1NameOnCounter(genericPlayerX)
                    } else {
                        vm.updatePlayer1NameOnCounter(genericPlayerO)
                    }
                } else {
                    vm.updatePlayer1NameOnCounter(player2Name)
                }
            }
        }

        // Handle first player name when game is ongoing or finished
        if (play) {
            if (player1Name.isEmpty() || player1Name == emptyPlayerName) {
                if (isXFor1) {
                    vm.updatePlayer1NameOnCounter(genericPlayerX)
                } else {
                    vm.updatePlayer1NameOnCounter(genericPlayerO)
                }
            } else {
                vm.updatePlayer1NameOnCounter(player1Name)
            }
        }
    }

    @Composable
    fun SecondPlayerName(
        state: GameState,
        vm: GameViewModel = hiltViewModel(),
    ) {
        val context = LocalContext.current
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

        // Apply "CPU" name for second player name when needed in CPU Mode
        if (gameMode == GameMode.VsCPU) {
            if (!isCPUPlayer1) {
                vm.updatePlayer2NameOnCounter(cpuName)
            } else {
                vm.updatePlayer2NameOnCounter("")
            }
        }

        // Handle second player name when game is in setup fase/not started yet
        if (!play) {
            if (localPlayer1) {
                if (player2Name.isEmpty()) {
                    if (isXFor1) {
                        vm.updatePlayer2NameOnCounter(genericPlayerO)
                    } else {
                        vm.updatePlayer2NameOnCounter(genericPlayerX)
                    }
                } else {
                    vm.updatePlayer2NameOnCounter(player2Name)
                }
            } else {
                if (player1Name.isEmpty()) {
                    if (isXFor1) {
                        vm.updatePlayer2NameOnCounter(genericPlayerO)
                    } else {
                        vm.updatePlayer2NameOnCounter(genericPlayerX)
                    }
                } else {
                    vm.updatePlayer2NameOnCounter(player1Name)
                }
            }
        }

        // Handle second player name when game is ongoing or finished
        if (play) {
            if (player2Name.isEmpty() || player2Name == emptyPlayerName) {
                if (isXFor1) {
                    vm.updatePlayer2NameOnCounter(genericPlayerO)
                } else {
                    vm.updatePlayer2NameOnCounter(genericPlayerX)
                }
            } else {
                vm.updatePlayer2NameOnCounter(player2Name)
            }
        }
    }
}

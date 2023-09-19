package com.rafael.tictactoe.game.domain.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

class PlayersNamesDisplay() {

    @Composable
    fun firstPlayerName() {
        val vm = hiltViewModel<GameViewModel>()
        val context = LocalContext.current
        val isXFor1 by vm.isXFor1.collectAsState()
        val player1Name by vm.player1Name.collectAsState()
        val player2Name by vm.player2Name.collectAsState()
        val genericPlayerX = context.getString(R.string.player_x)
        val genericPlayerO = context.getString(R.string.player_o)
        val isCPUMode by vm.isCPUMode.collectAsState()
        val isCPUPlayer1 by vm.isCPUPlayer1.collectAsState()
        val localPlayer1 by vm.localPlayer1.collectAsState()
        val play by vm.play.collectAsState()
        val emptyPlayerName = context.getString(R.string.player)
        val cpuName = context.getString(R.string.cpu)

        if (isCPUMode) {
            if (isCPUPlayer1) {
                vm.showPlayer1Name(cpuName)
            } else {
                vm.showPlayer1Name("")
            }
        }
        //Handle first player name when game is in setup fase/not started yet
        if (!play) {
            if (localPlayer1) {
                if (player1Name.isEmpty()) {
                    if (isXFor1) {
                        vm.showPlayer1Name(genericPlayerX)
                    } else {
                        vm.showPlayer1Name(genericPlayerO)
                    }
                } else {
                    vm.showPlayer1Name(player1Name)
                }
            } else {
                if (player2Name.isEmpty()) {
                    if (isXFor1) {
                        vm.showPlayer1Name(genericPlayerX)
                    } else {
                        vm.showPlayer1Name(genericPlayerO)
                    }
                } else {
                    vm.showPlayer1Name(player2Name)
                }
            }
        }

        //Handle first player name when game is ongoing or finished
        if (play) {
            if (player1Name.isEmpty() || player1Name == "$emptyPlayerName") {
                if (isXFor1) {
                    vm.showPlayer1Name(genericPlayerX)
                } else {
                    vm.showPlayer1Name(genericPlayerO)
                }
            } else {
                vm.showPlayer1Name(player1Name)
            }
        }
    }

    @Composable
    fun secondPlayerName() {
        val vm = hiltViewModel<GameViewModel>()
        val context = LocalContext.current
        val isXFor1 by vm.isXFor1.collectAsState()
        val player1Name by vm.player1Name.collectAsState()
        val player2Name by vm.player2Name.collectAsState()
        val genericPlayerO = context.getString(R.string.player_o)
        val isCPUMode by vm.isCPUMode.collectAsState()
        val isCPUPlayer1 by vm.isCPUPlayer1.collectAsState()
        val genericPlayerX = context.getString(R.string.player_x)
        val localPlayer1 by vm.localPlayer1.collectAsState()
        val play by vm.play.collectAsState()
        val emptyPlayerName = context.getString(R.string.player)
        val cpuName = context.getString(R.string.cpu)

        if (isCPUMode) {
            if (!isCPUPlayer1) {
                vm.showPlayer2Name(cpuName)
            } else {
                vm.showPlayer2Name("")
            }
        }

        //Handle second player name when game is in setup fase/not started yet
        if (!play) {
            if (localPlayer1) {
                if (player2Name.isEmpty()) {
                    if (isXFor1) {
                        vm.showPlayer2Name(genericPlayerO)
                    } else {
                        vm.showPlayer2Name(genericPlayerX)
                    }
                } else {
                    vm.showPlayer2Name(player2Name)
                }
            } else {
                if (player1Name.isEmpty()) {
                    if (isXFor1) {
                        vm.showPlayer2Name(genericPlayerO)
                    } else {
                        vm.showPlayer2Name(genericPlayerX)
                    }
                } else {
                    vm.showPlayer2Name(player1Name)
                }
            }
        }

        //Handle second player name when game is ongoing or finished
        if (play) {
            if (player2Name.isEmpty() || player2Name == "$emptyPlayerName") {
                if (isXFor1) {
                    vm.showPlayer2Name(genericPlayerO)
                } else {
                    vm.showPlayer2Name(genericPlayerX)
                }
            } else {
                vm.showPlayer2Name(player2Name)
            }
        }
    }
}
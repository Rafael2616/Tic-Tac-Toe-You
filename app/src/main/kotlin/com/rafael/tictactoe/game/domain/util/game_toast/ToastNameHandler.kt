package com.rafael.tictactoe.game.domain.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ToastNameHandler() {
    val vm = viewModel<GameViewModel>()
    val context = LocalContext.current
    val winner by vm.winner.collectAsState()
    val gotOld by vm.gotOld.collectAsState()
    val play by vm.play.collectAsState()
    val player1Name by vm.player1Name.collectAsState()
    val player2Name by vm.player2Name.collectAsState()
    val isPlayer1 by vm.isPlayer1.collectAsState()
    val playerName by vm.playerName.collectAsState()
    val isTimerOn by vm.isTimerOn.collectAsState()
    var timer by rememberSaveable { mutableIntStateOf(4) }
    val scope = rememberCoroutineScope()
    
     if (play && isTimerOn) {
        scope.launch {
            while (isTimerOn) {
                delay(500L)
                timer--
                if (timer >= 0) {
                    vm.updateCurrentPlayerName(timer.toString())
                }
                if (timer == 0) {
                    vm.updateIsTimerOn(false)
                }
            }
        }
    }

    if (!winner) {
        if (isPlayer1) {
            vm.updatePlayerName(player1Name)
        } else {
            vm.updatePlayerName(player2Name)
        }
    } else {
        if (!isPlayer1) {
            vm.updatePlayerName(player1Name)
        } else {
            vm.updatePlayerName(player2Name)
        }
    }

    if (player1Name.isEmpty() && player2Name.isEmpty()) {
		vm.updateCurrentPlayerName(context.getString(R.string.player))
    } else if (player1Name.isNotBlank() && player2Name.isNotBlank()) {
		vm.updateCurrentPlayerName(playerName)
    } else if (player1Name.isEmpty() && player2Name.isNotBlank() && isPlayer1) {
		vm.updateCurrentPlayerName(context.getString(R.string.player))
    } else if (player1Name.isEmpty() && player2Name.isNotBlank() && !isPlayer1) {
		vm.updateCurrentPlayerName(player2Name)
    } else if (player1Name.isNotBlank() && player2Name.isEmpty() && isPlayer1) {
		vm.updateCurrentPlayerName(player1Name)
    } else if (player1Name.isNotBlank() && player2Name.isEmpty() && !isPlayer1) {
		vm.updateCurrentPlayerName(context.getString(R.string.player))
    } else {
		vm.updateCurrentPlayerName("no name found")
    }
    
	 if (gotOld) {
		vm.updateCurrentPlayerName(context.getString(R.string.got_old))
    }
}

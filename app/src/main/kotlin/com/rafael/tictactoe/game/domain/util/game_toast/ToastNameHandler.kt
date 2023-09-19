package com.rafael.tictactoe.game.domain.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Composable
fun ToastNameHandler() {
    val vm = hiltViewModel<GameViewModel>()
    val context = LocalContext.current
	val winner by vm.winner.collectAsState()
    val gotOld by vm.gotOld.collectAsState()
    val play by vm.play.collectAsState()
    val player1Name by vm.player1Name.collectAsState()
    val player2Name by vm.player2Name.collectAsState()
    val isCPUPlayer1 by vm.isCPUPlayer1.collectAsState()
    val isPlayer1 by vm.isPlayer1.collectAsState()
    val is1Win by vm.is1Win.collectAsState()
    val isTimerOn by vm.isTimerOn.collectAsState()
    var timer by rememberSaveable { mutableIntStateOf(4) }
    val isCPUMode by vm.isCPUMode.collectAsState()
    val localPlayer1 by vm.localPlayer1.collectAsState()
    val emptyPlayerName = context.getString(R.string.player)
    val cpuName = context.getString(R.string.cpu)

    //Handle player name for case of is versus CPU Mode
    if (isCPUMode) {
        if (isCPUPlayer1) {
            vm.updatePlayer1Name(cpuName)
            if (player2Name == cpuName) {
                vm.updatePlayer2Name(player1Name)
            }
        } else {
            vm.updatePlayer2Name(cpuName)
            if (player1Name == cpuName) {
            vm.updatePlayer1Name(player2Name)
            }
        }
    }

    //Current player name for case of game not started yet
    if (!play) {
        if (localPlayer1) {
            if (player1Name.isEmpty()) {
               vm.updateCurrentPlayerName(emptyPlayerName)
           } else {
                vm.updateCurrentPlayerName(player1Name)
           }         
        } else {
            if (player2Name.isEmpty()) {
                vm.updateCurrentPlayerName(emptyPlayerName)
            } else {
                vm.updateCurrentPlayerName(player2Name)
            }
        }
    }
   
    //Counter showed when game starts
    if (play && isTimerOn) {
        CoroutineScope(Dispatchers.IO).launch {          
            while (isTimerOn) {
                delay(500L)
                synchronized(context) {
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
    }
   
    //Current player name for case of game is ongoing
    if (play && !winner && !isTimerOn) {
        if (isPlayer1) {
            if (player1Name.isEmpty()) {
                vm.updateCurrentPlayerName(emptyPlayerName)
                vm.updatePlayer1Name(emptyPlayerName)
           } else {
                vm.updateCurrentPlayerName(player1Name)
           }
        } else {
            if (player2Name.isEmpty()) {
                vm.updateCurrentPlayerName(emptyPlayerName)
                vm.updatePlayer2Name(emptyPlayerName)
            } else {
                vm.updateCurrentPlayerName(player2Name)
            }
        }
    }
 
   //Current player name for case of has winner   
    if (winner) {
        if (is1Win) {
            vm.updateCurrentPlayerName(player1Name)
        } else {
            vm.updateCurrentPlayerName(player2Name)
        }
    }

    //Current player name for case of got old
	 if (gotOld) {
		vm.updateCurrentPlayerName(context.getString(R.string.got_old))
    }
}
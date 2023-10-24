package com.rafael.tictactoe.game.domain.game_toast

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ToastNameHandler(state: GameState) {
    val vm = hiltViewModel<GameViewModel>()
    val context = LocalContext.current
	val winner = state.winner
    val gotOld = state.gotOld
    val play = state.play
    val player1Name = state.player1Name
    val player2Name = state.player2Name
    val isCPUPlayer1 = state.isCPUPlayer1
    val isPlayer1 = state.isPlayer1
    val is1Win = state.is1Win
    val isTimerOn = state.isTimerOn
    val isCPUMode = state.isCPUMode
    val localPlayer1 = state.localPlayer1
    var timer by rememberSaveable { mutableIntStateOf(4) }
    val emptyPlayerName = context.getString(R.string.player)
    val cpuName = context.getString(R.string.cpu)
    
    /*
     *CPU Mode only
     *Handle player name for case of is versus CPU Mode
     */
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

    /*
     *Both CPU and PvP Modes
     *Current player name for case of game not started yet
     */
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
	 if (gotOld && !winner) {
		vm.updateCurrentPlayerName(context.getString(R.string.got_old))
    }
}
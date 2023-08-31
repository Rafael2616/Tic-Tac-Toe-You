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
        val isPlayer1 by vm.isPlayer1.collectAsState()
        val genericPlayerX = context.getString(R.string.player_x)
        val isCPUMode by vm.isCPUMode.collectAsState()

        if (isCPUMode) vm.updatePlayer2Name("CPU")
    
	    if (isXFor1) {
 	       if (isPlayer1) {
  	          if (player1Name.isEmpty()) {
				    vm.showPlayer1Name(genericPlayerX)
  		      } else {
					vm.showPlayer1Name(player1Name)
     	       }
   	     } else {
    	        if (player2Name.isEmpty()) {
					vm.showPlayer1Name(genericPlayerX)
      	      } else {
					vm.showPlayer1Name(player2Name)
       	     }
     	   }
 	   } else {
   	     if (isPlayer1) {
   	         if (player2Name.isEmpty()) {
					vm.showPlayer1Name(genericPlayerX)
   	         } else {
					vm.showPlayer1Name(player2Name)
     	       }
    	    } else {
    	        if (player1Name.isEmpty()) {
					vm.showPlayer1Name(genericPlayerX)
    	        } else {
					vm.showPlayer1Name(player1Name)
				}
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
        val isPlayer1 by vm.isPlayer1.collectAsState()
	    val genericPlayerO = context.getString(R.string.player_o)

    	if (isXFor1) {
	    	if (isPlayer1) {
			    if (player2Name.isEmpty()) {
				    vm.showPlayer2Name(genericPlayerO)
				} else {
					vm.showPlayer2Name(player2Name)
    	        }
  	      } else {
   	     	if (player1Name.isEmpty()) {
					vm.showPlayer2Name(genericPlayerO)
    	        } else {
					vm.showPlayer2Name(player1Name)
   	         }
    	    }
   	 } else {
   	     if (isPlayer1) {
    	        if (player1Name.isEmpty()) {
					vm.showPlayer2Name(genericPlayerO)
   	 	    } else {
					vm.showPlayer2Name(player1Name)
    	        }
   	     } else {
    	        if (player2Name.isEmpty()) {
					vm.showPlayer2Name(genericPlayerO)
    		    } else {
					vm.showPlayer2Name(player2Name)
		        }
			}
		}
	}
}
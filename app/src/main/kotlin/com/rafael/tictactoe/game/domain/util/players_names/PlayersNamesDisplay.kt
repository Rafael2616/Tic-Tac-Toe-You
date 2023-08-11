package com.rafael.tictactoe.game.domain.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

class PlayersNamesDisplay() {

    @Composable
	fun firstPlayerName() {
        val vm = viewModel<GameViewModel>()
        val context = LocalContext.current
	    val play by vm.play.collectAsState()
        val isXFor1 by vm.isXFor1.collectAsState()
        val player1Name by vm.player1Name.collectAsState()
        val player2Name by vm.player2Name.collectAsState()
        val isPlayer1 by vm.isPlayer1.collectAsState()
        val player1InToast by vm.player1InToast.collectAsState()
        val genericPlayerX = context.getString(R.string.player_x)

	    if(!play) vm.updatePlayer1InToast(isPlayer1)

	    if (isXFor1) {
 	       if (player1InToast) {
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
   	     if (player1InToast) {
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
        val vm = viewModel<GameViewModel>()
        val context = LocalContext.current
	    val play by vm.play.collectAsState()
        val isXFor1 by vm.isXFor1.collectAsState()
        val player1Name by vm.player1Name.collectAsState()
        val player2Name by vm.player2Name.collectAsState()
        val isPlayer1 by vm.isPlayer1.collectAsState()
        val player1InToast by vm.player1InToast.collectAsState()
	    val genericPlayerO = context.getString(R.string.player_o)

	    if(!play) vm.updatePlayer1InToast(isPlayer1)

    	if (isXFor1) {
	    	if (player1InToast) {
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
   	     if (player1InToast) {
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

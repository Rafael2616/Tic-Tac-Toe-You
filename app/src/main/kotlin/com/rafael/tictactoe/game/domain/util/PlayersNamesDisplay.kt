package com.rafael.tictactoe.game.domain.util

import android.content.Context
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import javax.inject.Inject

class PlayersNamesDisplay() {

	fun firstPlayerName(
	context: Context,
	vm: GameViewModel,
	play: Boolean,
	isPlayer1: Boolean,
	isXFor1: Boolean,
	player1Name: String,
	player2Name: String,
	player1InToast: Boolean
	) {

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

	fun secondPlayerName(
	context: Context,
	vm: GameViewModel,
	play: Boolean,
	isPlayer1: Boolean,
	isXFor1: Boolean,
	player1Name: String,
	player2Name: String,
	player1InToast: Boolean
	) {

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
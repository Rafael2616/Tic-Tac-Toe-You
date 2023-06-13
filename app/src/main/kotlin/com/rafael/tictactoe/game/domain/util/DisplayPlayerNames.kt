package com.rafael.tictactoe.game.domain.util

import android.content.Context
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.repository.GameRepository
import javax.inject.Inject

class DisplayPlayerNames @Inject constructor(
private val repository : GameRepository
) {

	suspend fun firstPlayerName(context: Context) {

    val genericPlayerX = context.getString(R.string.player_x)
	val play = repository.getPlay()
	val player1InToast = repository.getPlayer1InToast()
	val isPlayer1 = repository.getIsPlayer1()
	val isXFor1 = repository.getIsXFor1()
	val player1Name = repository.getPlayer1Name()
	val player2Name = repository.getPlayer2Name()

	//if(!play) player1InToast = isPlayer1

	if (isXFor1) {
 	   if (player1InToast) {
  	      if (player1Name.isEmpty()) {
   	         repository.setShowPlayer1Name(genericPlayerX)
  		      } else {
     	          repository.setShowPlayer1Name(player1Name)
     	       }
   	     } else {
    	        if (player2Name.isEmpty()) {
					repository.setShowPlayer1Name(genericPlayerX)
      	      } else {
     	           repository.setShowPlayer1Name(player2Name)
       	     }
     	   }
 	   } else {
   	     if (player1InToast) {
   	         if (player2Name.isEmpty()) {
    	            repository.setShowPlayer1Name(genericPlayerX)
   	         } else {
    	            repository.setShowPlayer1Name(player2Name)
     	       }
    	    } else {
    	        if (player1Name.isEmpty()) {
    	            repository.setShowPlayer1Name(genericPlayerX)
    	        } else {
      	          repository.setShowPlayer1Name(player1Name)
				}
			}
		}
	}

	suspend fun secondPlayerName(context: Context) {

	val genericPlayerO = context.getString(R.string.player_o)
	val play = repository.getPlay()
	val player1InToast = repository.getPlayer1InToast()
	val isPlayer1 = repository.getIsPlayer1()
	val isXFor1 = repository.getIsXFor1()
	val player1Name = repository.getPlayer1Name()
	val player2Name = repository.getPlayer2Name()

	//if(!play) player1InToast = isPlayer1

	if (isXFor1) {
		if (player1InToast) {
			if (player2Name.isEmpty()) {
				repository.setShowPlayer2Name(genericPlayerO)
				} else {
   	  	       repository.setShowPlayer2Name(player2Name)
    	        }
  	      } else {
   	         if (player1Name.isEmpty()) {
     	           repository.setShowPlayer2Name(genericPlayerO)
    	        } else {
    	            repository.setShowPlayer2Name(player1Name)
   	         }
    	    }
   	 } else {
   	     if (player1InToast) {
    	        if (player1Name.isEmpty()) {
     	           repository.setShowPlayer2Name(genericPlayerO)
   	 	    } else {
    	            repository.setShowPlayer2Name(player1Name)
    	        }
   	     } else {
    	        if (player2Name.isEmpty()) {
    	            repository.setShowPlayer2Name(genericPlayerO)
    		    } else {
    	            repository.setShowPlayer2Name(player2Name)
		        }
			}
		}
	}
}
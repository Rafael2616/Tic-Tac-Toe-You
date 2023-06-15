package com.rafael.tictactoe.game.domain.util

import android.content.Context
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameEntity
import com.rafael.tictactoe.game.domain.repository.GameRepository
import javax.inject.Inject

class DisplayPlayerNames @Inject constructor (
private val repository : GameRepository
) {

	suspend fun firstPlayerName(context: Context) {

	val gameDefaults = GameEntity.defaultGameValues()
    val game = repository.getGameValues() ?: gameDefaults
    val genericPlayerX = context.getString(R.string.player_x)
	val play = game.play
	val player1InToast = game.player1InToast
	val isPlayer1 = game.isPlayer1
	val isXFor1 = game.isXFor1
	val player1Name = game.player1Name
	val player2Name = game.player2Name

	//if(!play) player1InToast = isPlayer1

	if (isXFor1) {
 	   if (player1InToast) {
  	      if (player1Name.isEmpty()) {
				game.showPlayer1Name = genericPlayerX
 			   repository.setGameValues(game)
  		      } else {
					game.showPlayer1Name = player1Name
 			  	 repository.setGameValues(game)
     	       }
   	     } else {
    	        if (player2Name.isEmpty()) {
					game.showPlayer1Name = genericPlayerX
 			  	 repository.setGameValues(game)
      	      } else {
					game.showPlayer1Name = player2Name
 			  	 repository.setGameValues(game)
       	     }
     	   }
 	   } else {
   	     if (player1InToast) {
   	         if (player2Name.isEmpty()) {
					game.showPlayer1Name = genericPlayerX
 			  	 repository.setGameValues(game)
   	         } else {
					game.showPlayer1Name = player2Name
 			  	 repository.setGameValues(game)
     	       }
    	    } else {
    	        if (player1Name.isEmpty()) {
					game.showPlayer1Name = genericPlayerX
 			  	 repository.setGameValues(game)
    	        } else {
					game.showPlayer1Name = player1Name
 			  	 repository.setGameValues(game)
				}
			}
		}
	}

	suspend fun secondPlayerName(context: Context) {

	val gameDefaults = GameEntity.defaultGameValues()
    val game = repository.getGameValues() ?: gameDefaults
	val genericPlayerO = context.getString(R.string.player_o)
	val play = game.play
	val player1InToast = game.player1InToast
	val isPlayer1 = game.isPlayer1
	val isXFor1 = game.isXFor1
	val player1Name = game.player1Name
	val player2Name = game.player2Name

	//if(!play) player1InToast = isPlayer1

	if (isXFor1) {
		if (player1InToast) {
			if (player2Name.isEmpty()) {
				game.showPlayer2Name = genericPlayerO
 			   repository.setGameValues(game)
				} else {
					game.showPlayer2Name = player2Name
 				   repository.setGameValues(game)
    	        }
  	      } else {
   	     	if (player1Name.isEmpty()) {
					game.showPlayer2Name = genericPlayerO
 				   repository.setGameValues(game)
    	        } else {
					game.showPlayer2Name = player1Name
 				   repository.setGameValues(game)
   	         }
    	    }
   	 } else {
   	     if (player1InToast) {
    	        if (player1Name.isEmpty()) {
					game.showPlayer2Name = genericPlayerO
 				   repository.setGameValues(game)
   	 	    } else {
					game.showPlayer2Name = player1Name
 				   repository.setGameValues(game)
    	        }
   	     } else {
    	        if (player2Name.isEmpty()) {
					game.showPlayer2Name = genericPlayerO
 				   repository.setGameValues(game)
    		    } else {
					game.showPlayer2Name = player2Name
 				   repository.setGameValues(game)
		        }
			}
		}
	}
}
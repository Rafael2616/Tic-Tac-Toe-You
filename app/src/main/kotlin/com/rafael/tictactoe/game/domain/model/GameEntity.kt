package com.rafael.tictactoe.game.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
@TypeConverters(BooleanListConverter::class)
data class GameEntity(
    @PrimaryKey
    var currentPlayerName: String,

    var player1Name: String,
    var player2Name: String,

    var player1Wins: Int,
    var player2Wins: Int,

    var cards: List<Boolean>,
    var isCardClicked: List<Boolean>,

    var play: Boolean,
    var isXImage: Boolean,
    var is1Win: Boolean,
    var isXFor1: Boolean,
    var isPlayer1: Boolean,
    var player1InToast: Boolean,

    var isScoreUp: Boolean,
    var expandButton: Boolean,
    var showRestartButton: Boolean,
    var isWinSoundPlaying: Boolean,
    var isGotOldSoundPlaying: Boolean,

    var startX: Float,
    var startY: Float,
    var endX: Float,
    var endY: Float,

    var showPlayer1Name: String,
    var showPlayer2Name: String,

    var winner: Boolean,
    var gotOld: Boolean
) {
    companion object {
        fun defaultGameValues(): GameEntity {
			return GameEntity(
      	      currentPlayerName = "",
      	      player1Name = "",
      	      player2Name = "",
       	     player1Wins = 0,
      	      player2Wins = 0,
     	       cards = List(9) {false},
      	      isCardClicked = List(9) {false},
      	      play = false,
      	      isXImage = false,
      	      is1Win = false,
    	        isXFor1 = false,
     	       isPlayer1 = true,
     	       player1InToast = true,
      	      isScoreUp = false,
      	      expandButton = false,
     	       showRestartButton = false,
     	       isWinSoundPlaying = false,
     	       isGotOldSoundPlaying = false,
   	         startX = 0f,
     	       startY = 0f,
    	        endX = 0f,
   	         endY = 0f,
   	         showPlayer1Name = "",
  	          showPlayer2Name = "",
  	          winner = false,
  	          gotOld = false
            )
        }
    }
}
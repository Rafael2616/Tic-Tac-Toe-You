package com.rafael.tictactoe.game.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity
@TypeConverters(BooleanListConverter::class)
data class GameEntity(
  @PrimaryKey
  var player1Name: String,
  var player2Name: String,

  var player1Wins: Int,
  var player2Wins: Int,

  var cards: List<Boolean>,
  var isCardClicked: List<Boolean>,
  
  var isScoreUp: Boolean,
  var isXImage: Boolean,
  var is1Win: Boolean,
  var isXFor1: Boolean,
  var isPlayer1: Boolean,
  var player1InToast: Boolean,
  var play: Boolean,
  var expandButton: Boolean,
  var isWinSoundPlaying: Boolean,
  var isGotOldSoundPlaying: Boolean,
  var isSoundEffectsOn: Boolean,
  var isBackgroundMusicOn: Boolean,

  var startX: Float,
  var startY: Float,
  var endX: Float,
  var endY: Float,

  var winner: Boolean,
  var gotOld: Boolean,
  
  var currentPlayerName: String

)
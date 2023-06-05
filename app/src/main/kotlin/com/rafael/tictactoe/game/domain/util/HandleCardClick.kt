package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import android.media.MediaPlayer
import androidx.compose.runtime.*
import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*

/*
@Composable
fun HandleCardClick(
repository: GameRepository,
index: Int,
//play: Boolean,
isCardClicked: List<Boolean>,
//isXImage: Boolean,
cards: List<Boolean>,
//isPlayer1: Boolean,
//isSoundEffectsOn: Boolean,
//winner: Boolean*/ /*
) = runBlocking {

 val clickSound1 = MediaPlayer.create(this, R.raw.click1_sound)
 val clickSound2 = MediaPlayer.create(this, R.raw.click2_sound) 


val play = repository.getPlay()
//isCardClicked = repository.get
val isXImage = repository.getIsXImage()
//cards = repository.get
val isPlayer1 = repository.getIsPlayer1()
val isSoundEffectsOn = repository.getIsSoundEffectsOn()
val winner = repository.getWinner()

if (!play){
 return
}

if (winner) {
    return
  }
  if (isCardClicked[index]) {
      return
     }
     if (!isCardClicked[index]) {
          if (isXImage){
             isCardClicked = isCardClicked.toMutableList().also {
               it[index] = true
               }
               cards = cards.toMutableList().also {
             it[index] = true
            }
        if (isSoundEffectsOn) {
         clickSound1.start()
           }
           repository.setIsXImage(false)
         repository.setIsPlayer1(!isPlayer1)
           }     
       else {
              isCardClicked = isCardClicked.toMutableList().also {
                it[index] = true
                }
               cards = cards.toMutableList().also {
             it[index] = false
            }
          if (isSoundEffectsOn) {
            clickSound2.start()
            }
           repository.setIsXImage(true)
           repository.setIsPlayer1(!isPlayer1)
        }
    }
}
*/
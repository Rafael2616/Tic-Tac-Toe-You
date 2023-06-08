package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*


fun SetPlayerImagePref(
repository: GameRepository
) = runBlocking {

val play = repository.getPlay()
val isXFor1 = repository.getIsXFor1()

if (!play){
    if (isXFor1){
        repository.setIsXImage(true)
  } else {
        repository.setIsXImage(false)
      }
   }
}
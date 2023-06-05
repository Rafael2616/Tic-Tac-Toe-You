package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.graphics.painter.*
import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*

/*
@Composable
fun SetCardImage(
index: Int,
isCardClicked: List<Boolean>,
cards: List<Boolean>,
repository: GameRepository
): Painter {

  val xImage = painterResource(R.drawable.x_image)
  val circleImage = painterResource(R.drawable.circle_image)
  val transparentImage = painterResource(R.drawable.transparent_image)

    val cardImage = if (isCardClicked[index]){
        if (cards[index]) xImage else circleImage
    } else {
        transparentImage
    }
    return cardImage
}
*/
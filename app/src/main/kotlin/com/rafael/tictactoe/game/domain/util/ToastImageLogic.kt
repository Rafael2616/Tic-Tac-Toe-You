package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import androidx.compose.ui.res.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.*
import com.rafael.tictactoe.game.domain.repository.GameRepository
import kotlinx.coroutines.*

//THIS FUNCTION RECEIVER VALUES DIRECTLY FROM VIEWMODEL
//TODO//REMVOE COMPOSE ELEMENTS OF THIS FUN

@Composable
fun ToastImageLogic(winner: Boolean, isXImage: Boolean): Painter {

 val xImageMax = painterResource(R.drawable.x_image_max_size)
 val circleImageMax = painterResource(R.drawable.circle_image_max_size)

return if (!winner) {
    if (isXImage) xImageMax else circleImageMax
}else {
    if(!isXImage) xImageMax else circleImageMax
   }         
}

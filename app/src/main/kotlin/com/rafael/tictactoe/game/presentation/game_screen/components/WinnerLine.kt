package com.rafael.tictactoe.game.presentation.components

import com.rafael.tictactoe.R
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.runtime.livedata.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.painter.*


@Composable
fun WinnerLine(
startX: Float,
startY: Float,
endX: Float,
endY: Float
) {

 Column(
      modifier = Modifier
     .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally
 ) {
     Canvas(
           modifier = Modifier
          .fillMaxSize()
          .weight(1f)
    ) {
         drawLine(
              color = Color.Yellow,
              strokeWidth = 20.dp.toPx(),
              start = Offset(x = startX, y = startY),
              end = Offset(x = endX, y = endY)
            )
        }          
    }
}
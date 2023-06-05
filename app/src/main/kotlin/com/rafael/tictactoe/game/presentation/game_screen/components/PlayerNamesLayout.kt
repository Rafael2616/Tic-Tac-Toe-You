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
fun PlayerNamesLayout(){

 Box(
     modifier = Modifier
     .fillMaxWidth(),
     contentAlignment = (Alignment.TopCenter)
  ) {
      Column(
             verticalArrangement = Arrangement.Center,
             modifier = Modifier
            .wrapContentSize()
            .padding(top = 24.dp, end = 80.dp)
         ) {
            Text(
                 text = ("hello") /*displayFirstPlayerNameLogic()*/,
                 style = MaterialTheme.typography.bodyMedium,
                 color = MaterialTheme.colorScheme.onSurface,                 
               )
        }
 }
 Box(
     modifier = Modifier
    .fillMaxWidth(),
     contentAlignment = (Alignment.TopCenter)
   ) {
      Column(
             verticalArrangement = Arrangement.Center,
             modifier = Modifier
            .wrapContentSize()
            .padding(top = 24.dp, start = 80.dp)
          ) {
             Text(
                  text = ("hello")/* displaySecondPlayerNameLogic()*/,
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                  )
            }
      }
}

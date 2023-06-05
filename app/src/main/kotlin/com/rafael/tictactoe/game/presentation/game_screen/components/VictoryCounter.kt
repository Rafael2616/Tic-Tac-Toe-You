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
//import com.rafael.tictactoe.game.domain.util.VictoryCounterLogic


@Composable
fun VictoryCounter(
player1Wins: String,
player2Wins: String
) {

//VictoryCounterLogic()

  Box(
      modifier = Modifier
      .fillMaxWidth(),
      contentAlignment = (Alignment.TopCenter)
  ) {
      Column(
            verticalArrangement = Arrangement.Center,
             modifier = Modifier
            .wrapContentSize()
            .padding(top = 55.dp)
     ) {         
           Row(
              modifier = Modifier
             .wrapContentSize(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically            
       ) {
      Card(
           shape = MaterialTheme.shapes.medium,
           modifier = Modifier
          .width(65.dp)
          .height(55.dp)
       ) {
             Text(
                  text = "$player1Wins",
                  style = MaterialTheme.typography.titleLarge,
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier
                 .align(Alignment.CenterHorizontally)
                 )
           Image(
                 contentDescription = null,
                 modifier = Modifier.fillMaxSize(),
                 painter = painterResource(R.drawable.x_image)
                 )      
               }
       Spacer(
              modifier = Modifier
             .padding(6.dp)
             )            
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
           .width(65.dp)
           .height(55.dp)
        ) {
             Text(
                  text = "$player2Wins",
                  style = MaterialTheme.typography.titleLarge,
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier
                 .align(Alignment.CenterHorizontally)
                  )
            Image(
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  painter = painterResource(R.drawable.circle_image)
                  )    
               }
           }    
       }
   }
}
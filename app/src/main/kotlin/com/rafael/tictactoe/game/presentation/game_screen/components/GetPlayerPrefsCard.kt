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
//import com.rafael.tictactoe.game.domain.util.SetPlayerImagePref


@Composable
fun GetPlayerPrefsCard(
/*isXFor1: Boolean,
player1Name: String,
player2Name: String,

isPlayer1: Boolean,
play: Boolean,
player1InToast: Boolean*/
) {

var isXFor1 = false
var player1Name = "TODO"
var player2Name = "TODO"

var isPlayer1 = false
var play = false
var player1InToast = false

val xImage = painterResource(R.drawable.x_image)
val circleImage = painterResource(R.drawable.circle_image)
val maxLength = 10
var player1Value = if (player1InToast) player1Name else player2Name
var player2Value = if (player1InToast) player2Name else player1Name
    
      Box(
          modifier = Modifier
         .fillMaxSize()
   ) {
        Card(
            shape = MaterialTheme.shapes.large,
            border = BorderStroke(1.5.dp, Color.Gray),
            modifier = Modifier               
           .width(380.dp)
           .height(240.dp)
           .align(Alignment.Center)
       ) {
            Row(
                modifier = Modifier
               .fillMaxWidth()
               .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
          ) {
                TextField(
                      value = player1Value,
                      onValueChange = { if (it.length <= maxLength) {if (isPlayer1) player1Name = it
                      else player2Name = it} },
                      label = { Text( stringResource(id = R.string.player1_name)) },
                      placeholder = { Text( stringResource(id = if (isXFor1) R.string.player_x
                      else R.string.player_o)) },
                      modifier = Modifier
                     .weight(1f)
                      )
                      Card(
                           shape = MaterialTheme.shapes.large,
                           modifier = Modifier
                          .width(60.dp)
                          .height(85.dp)
                          .clickable(onClick = { /*uiClickSound(); SetPlayerImagePref(); isPlayer1 = !isPlayer1*/})
                       ) {
                Image(
                     contentDescription = null,
                     modifier = Modifier.fillMaxSize(),
                     painter = painterResource(R.drawable.switch_icon)
                   )    
               }
  TextField(
            value = player2Value,
            onValueChange = { if (it.length <= maxLength) { if (isPlayer1) player2Name = it
            else player1Name = it}},              
            label = { Text( stringResource(id = R.string.player2_name)) },
            placeholder = { Text( stringResource(id = if (isXFor1) R.string.player_o
            else R.string.player_x)) },
            modifier = Modifier
           .weight(1f)
      )
}

Row(
     modifier = Modifier
    .fillMaxWidth(),
     horizontalArrangement = Arrangement.SpaceBetween
 ) {  
      Image(
            contentDescription = null,
            painter = (if (isXFor1) xImage else circleImage),
            modifier = Modifier
           .width(120.dp)
           .height(60.dp)                         
            )  
            Card(
                 shape = MaterialTheme.shapes.large,
                 modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .clickable(onClick = { /*uiClickSound(); isXFor1 = !isXFor1; SetPlayerImagePref() */})   
            ) {         
                 Image(
                      contentDescription = null,
                      modifier = Modifier.fillMaxSize(),
                      painter = painterResource(R.drawable.switch_icon)
                     )    
                 }
       Image(
             contentDescription = null,
             painter = (if (isXFor1) circleImage else xImage),            
             modifier = Modifier
            .width(120.dp)
            .height(60.dp)
      )
}

Button(
       onClick = { /*uiClickSound(); */play = true },
       modifier = Modifier
      .width(100.dp).height(45.dp)
      .align(Alignment.CenterHorizontally),          
   ) {
         Box(
             modifier = Modifier
            .fillMaxHeight(),
             contentAlignment = Alignment.Center      
       ) {
           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.Center,
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(
                   text = stringResource(id = R.string.play),
                   style = MaterialTheme.typography.bodyLarge
                  )       
               }
            }
         }
      }
   }
}
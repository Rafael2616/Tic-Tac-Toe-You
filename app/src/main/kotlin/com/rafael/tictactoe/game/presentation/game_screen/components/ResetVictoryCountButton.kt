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
//import com.rafael.tictactoe.game.domain.util.ResetVictoryCounterLogic


@Composable
fun ResetVictoryCounterButton(){

Box(
    modifier = Modifier
   .fillMaxWidth(),
    contentAlignment = Alignment.TopStart,
 ) {
     Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier            
         .wrapContentSize()
         .padding(start = 65.dp)
         .padding(top = 55.dp)
    ) {
         Card(
              shape = MaterialTheme.shapes.medium,  
              modifier = Modifier
             .width(45.dp)
             .height(45.dp)
             .clickable(onClick = { /*uiClickSound(); ResetVictoryCounterLogic()*/ })
        ) {
            Image(
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  painter = painterResource(R.drawable.trash_icon)
                 )      
               }
         Text(
              text = stringResource(R.string.reset),
              style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
              color = MaterialTheme.colorScheme.error,
              modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
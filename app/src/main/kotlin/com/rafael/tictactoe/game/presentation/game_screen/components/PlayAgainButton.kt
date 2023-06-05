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
//import com.rafael.tictactoe.game.domain.util.RestartGame


@Composable
fun PlayAgainButton() {

Box(
    modifier = Modifier
   .fillMaxWidth(),
    contentAlignment = Alignment.TopCenter
 ) {
     Column(
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
          modifier = Modifier          
         .fillMaxWidth()
         .padding(top = 210.dp)
     ) {
        Card(     
             shape = MaterialTheme.shapes.large,
             modifier = Modifier
            .width(75.dp)
            .height(75.dp)
            .clickable(onClick = {/*uiClickSound(); RestartGame() */})
         ) {
             Image(
                  contentDescription = null,
                  painter = painterResource(R.drawable.reset_icon),
                  modifier = Modifier
                 .width(75.dp)
              )
           }
     Text(
          text = stringResource(id = R.string.play_again),
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.onBackground
         )
      }
   }
}
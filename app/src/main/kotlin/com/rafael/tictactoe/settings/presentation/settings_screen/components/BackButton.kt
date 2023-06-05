package com.rafael.tictactoe.settings.presentation.settings_screen.components

import android.content.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.common.domain.util.UiClickSound

@Composable
fun BackButton(){

val myContext = LocalContext.current

Box(
    modifier = Modifier
   .fillMaxSize()
   .padding(top = 12.dp, start = 10.dp),
    contentAlignment = Alignment.TopStart
) {
      IconButton(
            onClick = { myContext.startActivity(Intent(myContext, MainActivity::class.java))/*; UiClickSound(myContext, isSoundEffectsOn)*/},
            modifier = Modifier
           .width(35.dp).height(35.dp)
     ) {
          Image(
               contentDescription = null,
               modifier = Modifier
              .height(35.dp)
              .width(35.dp),
               painter = painterResource(id = R.drawable.back_arrow)
               )
          }
     }
}
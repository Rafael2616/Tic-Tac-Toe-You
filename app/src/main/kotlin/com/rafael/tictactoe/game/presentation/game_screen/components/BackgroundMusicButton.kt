package com.rafael.tictactoe.game.presentation.components

import com.rafael.tictactoe.R
import android.content.Context
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
//import com.rafael.tictactoe.game.domain.util.BackgroundMusicLogic
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository


@Composable
fun BackgroundMusicButton(
isBackgroundMusicOn: Boolean,
context: Context
){

    Box(
        modifier = Modifier
       .wrapContentSize(),
        contentAlignment = Alignment.TopStart
 ) {
     Column(
             verticalArrangement = Arrangement.Center,
             modifier = Modifier
            .wrapContentSize()
            .padding(start = 15.dp)
            .padding(top = 165.dp)
    ) {
         Card(
              shape = MaterialTheme.shapes.medium,
              modifier = Modifier
             .width(45.dp)
             .height(45.dp)
             .clickable(onClick = {/*uiClickSound();*/ BackgroundMusicLogic(GameRepository, SettingsRepository, context)})
       ) {
            Image(
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  painter = painterResource( if (isBackgroundMusicOn) R.drawable.sound_on_icon
                  else R.drawable.sound_off_icon)
                 )      
               }
         Text(
              text = stringResource(R.string.active_music),
              style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

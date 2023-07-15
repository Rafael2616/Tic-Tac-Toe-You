package com.rafael.tictactoe.game.presentation.main_screen.components

import android.content.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.presentation.game_screen.activities.GameActivity
import com.rafael.tictactoe.game.presentation.game_screen.activities.TestGameActivity
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.presentation.settings_screen.activities.SettingsActivity

@Composable
fun OptionsButtons(settingsRepo: SettingsRepository){

val mContext = LocalContext.current   
var cards by rememberSaveable { mutableStateOf(List<Boolean>(2) { false} ) }

LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
 ) {
     cards.forEachIndexed { index, _ ->
        Card(
             shape = MaterialTheme.shapes.large,
             modifier = Modifier
            .width(240.dp)
            .height(65.dp)
            .clickable(onClick = { UiClickSound(mContext, settingsRepo);
             when (index) {
               0 -> mContext.startActivity(Intent(mContext, TestGameActivity::class.java))
               1 -> mContext.startActivity(Intent(mContext, SettingsActivity::class.java))}})   
         ) {  
              Box(
                   modifier = Modifier
                  .fillMaxHeight(),
                   contentAlignment = Alignment.Center      
             ) {
                    Row(
                        modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 18.dp),
                         horizontalArrangement = Arrangement.Center,
                         verticalAlignment = Alignment.CenterVertically,                     
                    ) {
                         Image(
                               contentDescription = null,
                               modifier = Modifier
                              .height(60.dp)   
                              .width(60.dp),
                               painter = painterResource(
                              id = when (index) {
                                0 -> R.drawable.play_icon
                                1 -> R.drawable.settings_icon
                             else -> R.drawable.transparent_image
                              }                        
                             )
                            )
                      Text(
                           style = MaterialTheme.typography.titleLarge,
                           color = MaterialTheme.colorScheme.onSurface,
                           overflow = TextOverflow.Ellipsis,
                           softWrap = true,
                           text = when (index) {
                             0 -> stringResource(id = R.string.play)
                             1 -> stringResource(id = R.string.settings)
                          else -> ("index error")
                         }
                      )
                   }
                }
             }
         Spacer(modifier = Modifier.height(20.dp))
      }
   }
}

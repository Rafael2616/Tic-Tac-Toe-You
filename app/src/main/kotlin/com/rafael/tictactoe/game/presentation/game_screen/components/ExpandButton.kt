package com.rafael.tictactoe.game.presentation.game_screen.components

import android.content.Context
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.domain.util.ExpandButtonLogic
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.launch


@Composable
fun ExpandButton(context: Context, repository: GameRepository, settingsRepo: SettingsRepository, expandButton: Boolean) {

    val scope = rememberCoroutineScope()

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
		.padding(top = 60.dp)
     ) {
         Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            modifier = Modifier
            .width(45.dp)
            .height(35.dp)
			.clickable(onClick = { UiClickSound(context, settingsRepo); scope.launch { ExpandButtonLogic(repository)}})
 	    ) {
			Image(
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(if (expandButton) R.drawable.expand_up_icon
				else R.drawable.expand_down_icon)
                )      
            }       
        }
    }
}
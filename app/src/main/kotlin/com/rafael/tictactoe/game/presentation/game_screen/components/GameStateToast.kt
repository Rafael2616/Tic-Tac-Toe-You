package com.rafael.tictactoe.game.presentation.game_screen.components

import android.content.Context
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.GameStateToastLogic
import com.rafael.tictactoe.game.domain.util.ToastImageLogic
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

//Some work needed in this file

@Composable
fun GameStateToast(
context: Context,
vm: GameViewModel,
isXImage: Boolean,
winner: Boolean,
isPlayer1: Boolean,
gotOld: Boolean,
player1Name: String,
player2Name: String,
currentPlayerName: String,
isXFor1: Boolean
) {
	val xImageMax = painterResource(R.drawable.x_image_max_size)
    val circleImageMax = painterResource(R.drawable.circle_image_max_size)

    GameStateToastLogic(context, vm, winner, isPlayer1, gotOld, player1Name, player2Name)

	val image = if (!winner) {
   	 if (isXFor1) xImageMax else circleImageMax
	}else {
    	if(!isXFor1) xImageMax else circleImageMax
	} 
	
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 140.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$currentPlayerName",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (!gotOld) MaterialTheme.colorScheme.onSurface
                        else MaterialTheme.colorScheme.error,
                    )
                    if (!gotOld) {
                        Spacer(modifier = Modifier.padding(3.dp))
                        Image(
                            contentDescription = null,
                            painter = image, //painterResource(id = R.drawable.transparent_image),
						//	ToastImageLogic(context, winner, isXImage, xImageMax, circleImageMax) as Painter,
                            modifier = Modifier.height(17.dp).width(17.dp)
                        )
                    }
                    if (winner) {
                        Spacer(modifier = Modifier.padding(3.dp))
                        Text(
                            text = stringResource(R.string.winner),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Green
                        )
                    }
                }
            }
        }
    }
}
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
import com.rafael.tictactoe.game.domain.util.RestartGame
import com.rafael.tictactoe.game.domain.util.RestartButtonLogic
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun PlayAgainButton(
	context: Context,
	viewModel: GameViewModel,
	settingsVM: SettingsViewModel
) {

    val winner by viewModel.winner.collectAsState()
    val gotOld by viewModel.gotOld.collectAsState()
    val isCardClicked by viewModel.isCardClicked.collectAsState()
	val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()
	
	RestartButtonLogic(viewModel, isCardClicked, winner, gotOld)

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
                    .clickable(onClick = {
                        viewModel.updateExpandButton(false)
						UiClickSound(context, isSoundEffectsOn)
             		   RestartGame(viewModel)
                    })
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
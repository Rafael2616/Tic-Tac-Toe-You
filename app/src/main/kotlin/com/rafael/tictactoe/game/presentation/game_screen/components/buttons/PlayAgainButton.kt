package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.RandomCardSorter
import com.rafael.tictactoe.game.domain.util.RestartGame
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun PlayAgainButton() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val isCPUPlayer1 by viewModel.isCPUPlayer1.collectAsState()
    val isCPUMode by viewModel.isCPUMode.collectAsState()
    val is1Win by viewModel.is1Win.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 210.dp)
        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
                    .clickable(onClick = {
                        viewModel.showExpandButton(false)
						settingsVM.execUIClickSound(context)
             		   RestartGame(viewModel)
                        if (isCPUMode) {
                            if (isCPUPlayer1 && is1Win || !isCPUPlayer1 && !is1Win) {
                                RandomCardSorter(viewModel, context)
                            }
                        }
                    })
            ) {
                Image(
                    contentDescription = null,
                    painter = painterResource(R.drawable.reset_icon),
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Text(
                text = stringResource(id = R.string.play_again),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
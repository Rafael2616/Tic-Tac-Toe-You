package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.domain.util.ToastImageHandler

@Composable
fun GameStateToast() {
    val viewModel = viewModel<GameViewModel>()
    val currentPlayerName by viewModel.currentPlayerName.collectAsState()
    val winner by viewModel.winner.collectAsState()
    val gotOld by viewModel.gotOld.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
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
                            painter = ToastImageHandler(),
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
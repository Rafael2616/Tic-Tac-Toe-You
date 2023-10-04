package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.ToastImageHandler
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun GameStateToast() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val currentPlayerName by viewModel.currentPlayerName.collectAsState()
    val winner by viewModel.winner.collectAsState()
    val gotOld by viewModel.gotOld.collectAsState()
    val isDarkThemeOn by settingsVM.isDarkThemeOn.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 140.dp),
        contentAlignment = Alignment.TopCenter
    ) {
         Row(
            modifier = Modifier
                .wrapContentSize()
                .height(35.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$currentPlayerName",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = if (!gotOld) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.error,
            )
            if (!gotOld) {
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier.size(15.dp)
                ) {
                    ToastImageHandler()
                }
            }
            if (winner) {
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.winner),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = if (isDarkThemeOn) Color.Green else Color(0xFF229A00)
                )
            }
        }
    }
}
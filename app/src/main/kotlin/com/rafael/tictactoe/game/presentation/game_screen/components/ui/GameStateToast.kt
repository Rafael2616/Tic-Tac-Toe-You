package com.rafael.tictactoe.game.presentation.gameScreen.components.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.toast.ToastImageHandler
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun GameStateToast(
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)
    val color = if (!state.gotOld) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.error
    }
    val textColor by animateColorAsState(color)
    val winnerString = stringResource(R.string.winner)
    val winnerState = if (state.hasWinner) winnerString else ""

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopCenter,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(if (isInLandscape) 0.48f else 1f)
                .padding(top = 140.dp)
                .height(35.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AnimatedContent(
                targetState = state.currentPlayerName,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
            ) { currentPlayerName ->
                Text(
                    text = currentPlayerName,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = textColor,
                )
            }
            if (!state.gotOld) {
                Spacer(Modifier.width(6.dp))
            }
            AnimatedVisibility(
                visible = !state.gotOld,
                enter = scaleIn(),
                exit = scaleOut(),
            ) {
                Box(
                    modifier = Modifier.size(15.dp),
                ) {
                    ToastImageHandler(state, settingsState)
                }
            }
            if (state.hasWinner) {
                Spacer(Modifier.width(6.dp))
            }
            AnimatedContent(
                targetState = winnerState,
                transitionSpec = {
                    slideInHorizontally() togetherWith slideOutHorizontally()
                },
            ) { winnerText ->
                Text(
                    text = winnerText,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = if (settingsState.isDarkThemeOn) Color.Green else Color(0xFF229A00),
                )
            }
        }
    }
}

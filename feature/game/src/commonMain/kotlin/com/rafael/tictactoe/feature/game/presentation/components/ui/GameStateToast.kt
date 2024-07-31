package com.rafael.tictactoe.feature.game.presentation.components.ui

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.core.platform.configuration.isInLandscape
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.winner
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun GameStateToast(
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val color = if (!state.gotOld) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.error
    }
    val textColor by animateColorAsState(color, label = "")
    val winnerString = stringResource(Res.string.winner)
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
                label = "",
            ) { currentPlayerName ->
                Text(
                    text = currentPlayerName,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
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
                    ToastImage(state, settingsState)
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
                label = "",
            ) { winnerText ->
                Text(
                    text = winnerText,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
                    textAlign = TextAlign.Center,
                    color = if (settingsState.isDarkThemeOn) Color.Green else Color(0xFF229A00),
                )
            }
        }
    }
}

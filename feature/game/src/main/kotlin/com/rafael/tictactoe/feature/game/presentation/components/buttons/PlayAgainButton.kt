package com.rafael.tictactoe.game.gameScreen.presentation.components.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.Colors
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState

@Composable
fun PlayAgainButton(
    onEvent: (GameEvent) -> Unit,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(if (isInLandscape) 0.48f else 1f)
                .padding(top = 210.dp),
        ) {
            FilledTonalIconButton(
                modifier = Modifier
                    .size(75.dp),
                onClick = { onEvent(GameEvent.PlayAgain) },
            ) {
                Icon(
                    imageVector = Icons.Filled.Replay,
                    tint = if (settingsState.isDarkThemeOn) Color.Cyan else Colors.get().blue,
                    contentDescription = "Play Button",
                    modifier = Modifier
                        .size(68.dp),
                )
            }
            Spacer(
                modifier = Modifier.height(10.dp),
            )
            Text(
                text = stringResource(id = R.string.play_again),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )
        }
    }
}

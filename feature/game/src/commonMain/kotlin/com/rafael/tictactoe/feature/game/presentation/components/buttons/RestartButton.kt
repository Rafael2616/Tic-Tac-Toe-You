package com.rafael.tictactoe.feature.game.presentation.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.core.domain.util.Colors
import com.rafael.tictactoe.core.game.domain.model.GameMode
import com.rafael.tictactoe.core.platform.configuration.isInLandscape
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.restart
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.game.domain.model.GameEvent
import com.rafael.tictactoe.feature.game.domain.model.GameState
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun RestartButton(
    onEvent: (GameEvent) -> Unit,
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val isCPUMode = state.gameMode == GameMode.VsCPU

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopEnd,
    ) {
        Box(
            modifier = Modifier
                .padding(top = if (isCPUMode) 70.dp else 55.dp, end = if (isInLandscape) 35.dp else 25.dp)
                .fillMaxWidth(if (isInLandscape) 0.46f else 1f),
            contentAlignment = Alignment.TopEnd,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.wrapContentSize(),
            ) {
                FilledTonalIconButton(
                    modifier = Modifier
                        .size(50.dp),
                    onClick = { onEvent(GameEvent.PlayAgain) },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Replay,
                        tint = if (settingsState.isDarkThemeOn) Color.Cyan else Colors.get().blue,
                        contentDescription = "Restart Button",
                        modifier = Modifier
                            .size(42.dp),
                    )
                }
                Text(
                    text = stringResource(Res.string.restart),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp),
                )
            }
        }
    }
}

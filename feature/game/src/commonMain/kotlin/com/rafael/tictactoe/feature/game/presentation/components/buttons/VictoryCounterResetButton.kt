package com.rafael.tictactoe.feature.game.presentation.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.core.platform.configuration.isInLandscape
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.reset
import com.rafael.tictactoe.feature.game.domain.model.GameEvent
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun VictoryCounterResetButton(
    onEvent: (GameEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(top = 55.dp, start = 65.dp)
            .fillMaxWidth(if (isInLandscape) 0.48f else 1f),
        contentAlignment = Alignment.TopStart,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            FilledTonalIconButton(
                modifier = Modifier
                    .size(45.dp),
                onClick = { onEvent(GameEvent.ResetVictoryCounter) },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Victory Counter Reset Button",
                    modifier = Modifier
                        .size(38.dp),
                    tint = MaterialTheme.colorScheme.error,
                )
            }
            Text(
                text = stringResource(Res.string.reset),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp),
            )
        }
    }
}

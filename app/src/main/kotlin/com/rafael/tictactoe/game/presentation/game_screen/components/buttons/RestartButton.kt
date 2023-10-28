package com.rafael.tictactoe.game.presentation.game_screen.components.buttons

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState

@Composable
fun RestartButton(
    onEvent: (GameEvent) -> Unit,
    state: GameState
) {
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)
    val isCPUMode = state.isCPUMode

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopEnd
    ) {
        Box(
            modifier = Modifier
                .padding(top = if (isCPUMode) 70.dp else 55.dp, end = if (isCPUMode) 45.dp else 30.dp)
                .fillMaxWidth(if (isInLandscape) 0.46f else 1f),
            contentAlignment = Alignment.TopEnd
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.wrapContentSize()
            ) {
                Card(
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                    ),
                    modifier = Modifier
                        .size(45.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                        .clickable {
                            onEvent(GameEvent.PlayAgain)
                        }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            contentDescription = "Restart Button",
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(R.drawable.reset_icon)
                        )
                    }
                }
                Text(
                    text = stringResource(R.string.restart),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
            }
        }
    }
}
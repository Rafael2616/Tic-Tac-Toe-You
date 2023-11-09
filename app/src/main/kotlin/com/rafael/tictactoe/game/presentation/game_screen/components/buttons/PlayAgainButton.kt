package com.rafael.tictactoe.game.presentation.game_screen.components.buttons

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun PlayAgainButton(
    onEvent: (GameEvent) -> Unit,
    settingsState: SettingsState
) {
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)

    Box(
        modifier = Modifier
           .fillMaxSize(),
        contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(if (isInLandscape) 0.48f else 1f)
                .padding(top = 210.dp)
        ) { 
            Card(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(20.dp)
                ),
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
                    .clickable {
                        onEvent(GameEvent.PlayAgain)
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Replay,
                        tint = if (settingsState.isDarkThemeOn) Color.Cyan else Colors.get().blue,
                        contentDescription = "Play Button",
                        modifier = Modifier
                            .size(68.dp)
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(10.dp)
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
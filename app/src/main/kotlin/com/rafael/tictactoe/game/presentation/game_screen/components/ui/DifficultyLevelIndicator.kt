package com.rafael.tictactoe.game.presentation.game_screen.components.ui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun DifficultyLevelIndicator(
    state: GameState,
    settingsState: SettingsState
) {
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)
    val level = state.cpuDifficultyLevel

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopEnd
    ) {
        Box(
            modifier = Modifier
                .padding(top = 30.dp, end = 40.dp)
                .fillMaxWidth(if (isInLandscape) 0.46f else 1f),
            contentAlignment = Alignment.TopEnd
        ) {
            Card(
                shape = MaterialTheme.shapes.medium,
                border = BorderStroke(1.5.dp, Color.Gray),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                ),
                modifier = Modifier
                    .width(68.dp)
                    .height(25.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp)
                        .padding(start = 5.dp, end = 3.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(state.difficultyLevelName),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp)
                    )
                    Icon(
                        imageVector = state.difficultyLevelIcon,
                        tint = when (level) {
                            CPUDifficultyLevel.Easy -> if (settingsState.isDarkThemeOn) Color.Green else Color(0xFF299961)
                            CPUDifficultyLevel.Medium -> if (settingsState.isDarkThemeOn) Color.Yellow else Colors.get().burnt_yellow
                            CPUDifficultyLevel.Hard -> Color.Red
                        },
                        contentDescription = "Icon",
       	    	     modifier = Modifier.size(23.dp)
                    )
                }
            }
        }
    }
}
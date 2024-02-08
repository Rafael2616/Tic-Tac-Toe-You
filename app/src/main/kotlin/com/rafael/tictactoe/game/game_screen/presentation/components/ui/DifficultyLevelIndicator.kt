package com.rafael.tictactoe.game.gameScreen.presentation.components.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Colors
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState

@Composable
fun DifficultyLevelIndicator(
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE
    val level = state.cpuDifficultyLevel

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopEnd,
    ) {
        Box(
            modifier = Modifier
                .padding(top = 25.dp, end = 15.dp)
                .fillMaxWidth(if (isInLandscape) 0.46f else 1f),
            contentAlignment = Alignment.TopEnd,
        ) {
            AssistChip(
                onClick = { },
                label = {
                    Text(
                        text = stringResource(state.difficultyLevelName),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = state.difficultyLevelIcon,
                        contentDescription = "Icon",
                        Modifier.size(AssistChipDefaults.IconSize),
                        tint = when (level) {
                            CPUDifficultyLevel.Easy -> if (settingsState.isDarkThemeOn) Color.Green else Color(0xFF299961)
                            CPUDifficultyLevel.Medium -> if (settingsState.isDarkThemeOn) Color.Yellow else Colors.get().burnt_yellow
                            CPUDifficultyLevel.Hard -> Color.Red
                        },
                    )
                },
            )
        }
    }
}

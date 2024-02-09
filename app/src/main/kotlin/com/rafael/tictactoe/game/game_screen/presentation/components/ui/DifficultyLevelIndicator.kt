package com.rafael.tictactoe.game.gameScreen.presentation.components.ui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.model.Level
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Colors
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterial3Api
fun DifficultyLevelIndicator(
    state: GameState,
    onEvent: (GameEvent) -> Unit,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val levels = Level.getLevels(settingsState.isDarkThemeOn)
    var showSelector by remember { mutableStateOf(false) }

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
                onClick = {
                    if (!state.isGameFinished && state.isCardClicked.flatten().any { it }) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Termine a partida ou começe outra do zero \n" +
                                    "para mudar a dificuldade",
                            )
                        }
                    } else {
                        showSelector = true
                    }
                },
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
                        tint = when (state.cpuDifficultyLevel) {
                            CPUDifficultyLevel.Easy -> if (settingsState.isDarkThemeOn) Color.Green else Color(0xFF299961)
                            CPUDifficultyLevel.Medium -> if (settingsState.isDarkThemeOn) Color.Yellow else Colors.get().burnt_yellow
                            CPUDifficultyLevel.Hard -> Color.Red
                        },
                    )
                },
            )
        }

        if (showSelector) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center,
            ) {
                Card(
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(1.25.dp, Color.Gray),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(18.dp),
                    ),
                    modifier = Modifier
                        .wrapContentSize(),
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("Select the new Level")
                        SingleChoiceSegmentedButtonRow {
                            levels.forEachIndexed { index, level ->
                                SegmentedButton(
                                    shape = SegmentedButtonDefaults.itemShape(index = index, count = levels.size),
                                    icon = {
                                        SegmentedButtonDefaults.Icon(active = index == state.selectedDifficultyLevel) {
                                            Icon(
                                                imageVector = level.icon,
                                                contentDescription = null,
                                                tint = level.tint,
                                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize),
                                            )
                                        }
                                    },
                                    onClick = {
                                        onEvent(
                                            GameEvent.SelectCPUDifficultyMode(
                                                level = level.level,
                                                selectedLevel = index,
                                                icon = level.icon,
                                                name = level.name,
                                            ),
                                        )
                                        scope.launch {
                                            delay(500L)
                                            showSelector = false
                                        }
                                    },
                                    selected = index == state.selectedDifficultyLevel,
                                    colors = SegmentedButtonDefaults.colors(
                                        inactiveContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp),
                                    ),
                                ) {
                                    Text(stringResource(level.name))
                                }
                            }
                        } 
                    }
                }
            }
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
        ) {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier
                        .padding(12.dp),
                ) {
                    Text(data.visuals.message)
                }
            }
        }
    } 
}

package com.rafael.tictactoe.feature.game.presentation.components.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.BasicAlertDialog
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
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.game.domain.model.GameEvent
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.game.domain.model.Level
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterial3Api
internal fun DifficultyLevelSwitcher(
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
    val snackbarMessage = stringResource(R.string.unable_to_switch_levels_msg)

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
                            snackbarHostState.showSnackbar(snackbarMessage)
                        }
                    } else {
                        showSelector = true
                    }
                },
                label = {
                    Text(
                        text = stringResource(state.selectedDifficultyLevel.name),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = state.selectedDifficultyLevel.icon,
                        contentDescription = "Icon",
                        Modifier.size(AssistChipDefaults.IconSize),
                        tint = state.selectedDifficultyLevel.tint,
                    )
                },
            )
        }

        AnimatedVisibility(showSelector) {
            BasicAlertDialog(
                onDismissRequest = { showSelector = false },
            ) {
                Card(
                    shape = MaterialTheme.shapes.medium,
                    border = BorderStroke(1.25.dp, Color.Gray),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp),
                    ),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(6.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(R.string.select_new_level),
                            modifier = Modifier.padding(4.dp),
                        )
                        SingleChoiceSegmentedButtonRow {
                            levels.forEachIndexed { index, level ->
                                SegmentedButton(
                                    shape = SegmentedButtonDefaults.itemShape(index = index, count = levels.size),
                                    icon = {
                                        SegmentedButtonDefaults.Icon(active = index == state.selectedDifficultyLevel.index) {
                                            Icon(
                                                imageVector = level.icon,
                                                contentDescription = null,
                                                tint = level.tint,
                                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize),
                                            )
                                        }
                                    },
                                    onClick = {
                                        onEvent(GameEvent.SelectCPUDifficultyMode(levels[index]))
                                        scope.launch {
                                            delay(200L)
                                            showSelector = false
                                        }
                                    },
                                    selected = index == state.selectedDifficultyLevel.index,
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

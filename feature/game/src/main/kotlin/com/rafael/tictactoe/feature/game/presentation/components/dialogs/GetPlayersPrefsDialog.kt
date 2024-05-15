package com.rafael.tictactoe.feature.game.presentation.components.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Brush
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.core.domain.model.Colors
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.core.resources.draws.drawCircleinDialog
import com.rafael.tictactoe.core.resources.draws.drawXinDialog
import com.rafael.tictactoe.feature.game.domain.model.GameEvent
import com.rafael.tictactoe.feature.game.domain.model.GameMode
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.game.domain.model.Level
import com.rafael.tictactoe.feature.settings.domain.enums.Customization
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState

@Composable
@ExperimentalLayoutApi
fun GetPlayersPrefsDialog(
    onEvent: (GameEvent) -> Unit,
    onSettingsEvent: (SettingsEvent) -> Unit,
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val view = LocalView.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current.density
    val densityHeight = (view.height / density).dp
    val focusRequester = remember { FocusRequester() }
    val xColor by animateColorAsState(settingsState.xColor)
    val circleColor by animateColorAsState(settingsState.circleColor)
    val isDarkThemeOn = settingsState.isDarkThemeOn
    val textField1State = if (state.isCPUPlayer1) false else true
    val dialogHeight by animateDpAsState(
        when (state.gameMode) {
            GameMode.VsCPU -> 270.dp
            GameMode.PvP -> 215.dp
            GameMode.Online -> 235.dp
        },
    )
    val dialogWidth by animateDpAsState(if (state.gameMode == GameMode.Online) 245.dp else 380.dp)
    val maxLetterLength by rememberSaveable { mutableIntStateOf(10) }
    val containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(18.dp)
    val bottomPadding by animateDpAsState(
        if (WindowInsets.isImeVisible) {
            WindowInsets.ime.asPaddingValues().calculateBottomPadding().plus(8.dp)
        } else {
            densityHeight / 2 - dialogHeight / 2
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
    )
    val levels = Level.getLevels(isDarkThemeOn)

    Surface(
        color = Color.Black.copy(alpha = 0.35f),
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { focusManager.clearFocus() },
                )
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding.coerceAtLeast(0.dp)),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.25.dp, Color.Gray),
                colors = CardDefaults.cardColors(
                    containerColor = containerColor,
                ),
                modifier = Modifier
                    .width(dialogWidth)
                    .height(dialogHeight),
            ) {
                // TextFields to get player names
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                        .then(
                            if (state.gameMode == GameMode.Online) {
                                Modifier.padding(horizontal = 15.dp)
                            } else {
                                Modifier
                            },
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val textFieldColors = TextFieldDefaults.colors(
                        focusedContainerColor = containerColor,
                        unfocusedContainerColor = containerColor,
                        disabledContainerColor = containerColor,
                    )
                    val keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.Words,
                    )

                    TextField(
                        value = if (state.localPlayer1) state.player1Name else state.player2Name,
                        onValueChange = {
                            if (it.length <= maxLetterLength) {
                                onEvent(GameEvent.GetFirstPlayerName(it))
                            }
                        },
                        label = {
                            Text(
                                text = stringResource(
                                    id =
                                    if (state.gameMode == GameMode.Online) R.string.type_your_name else R.string.player1_name,
                                ),
                            )
                        },
                        placeholder = {
                            Text(stringResource(id = if (state.isXFor1) R.string.player_x else R.string.player_o))
                        },
                        enabled = if (state.gameMode == GameMode.VsCPU) textField1State else true,
                        keyboardOptions = keyboardOptions,
                        colors = textFieldColors,
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester),
                    )

                    // Show an second Textfield and toggle only in PvP and CPU Modes
                    if (state.gameMode != GameMode.Online) {
                        IconButton(
                            modifier = Modifier
                                .size(45.dp),
                            onClick = { onEvent(GameEvent.SwitchPlayerPositions) },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.SwapHoriz,
                                tint = if (isDarkThemeOn) Color.Cyan else Colors.get().blue,
                                contentDescription = "Player Position Switch",
                                modifier = Modifier
                                    .size(45.dp),
                            )
                        }
                        TextField(
                            value = if (state.localPlayer1) state.player2Name else state.player1Name,
                            onValueChange = {
                                if (it.length <= maxLetterLength) {
                                    onEvent(GameEvent.GetSecondPlayerName(it))
                                }
                            },
                            label = {
                                Text(stringResource(id = R.string.player2_name))
                            },
                            placeholder = {
                                Text(stringResource(id = if (state.isXFor1) R.string.player_o else R.string.player_x))
                            },
                            enabled = if (state.gameMode == GameMode.VsCPU) !textField1State else true,
                            keyboardOptions = keyboardOptions,
                            colors = textFieldColors,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }

                // Player images preview and button to toggle them
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
                ) {
                    val canvasOnClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        onSettingsEvent(SettingsEvent.ShowColorSelectionDialog(true))
                    }
                    IconButton(
                        modifier = Modifier
                            .size(40.dp),
                        onClick = {
                            canvasOnClick()
                            onSettingsEvent(
                                SettingsEvent.SetCustomizationType(
                                    if (state.isXFor1) Customization.XCOLOR else Customization.CIRCLECOLOR,
                                ),
                            )
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Brush,
                            contentDescription = "Color image picker",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .size(25.dp),
                        )
                    }
                    Box(
                        modifier = Modifier.size(35.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Canvas(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            if (state.isXFor1) {
                                drawXinDialog(xColor)
                            } else {
                                drawCircleinDialog(circleColor)
                            }
                        }
                    }

                    // Show an second Image and toggle only in PvP and CPU Modes
                    if (state.gameMode != GameMode.Online) {
                        Spacer(modifier = Modifier.width(40.dp))
                        IconButton(
                            modifier = Modifier
                                .size(45.dp),
                            onClick = { onEvent(GameEvent.ToggleIsXForPlayer1) },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.SwapHoriz,
                                tint = if (isDarkThemeOn) Color.Cyan else Colors.get().blue,
                                contentDescription = "Player Icon Switch",
                                modifier = Modifier
                                    .size(45.dp),
                            )
                        }
                        Spacer(modifier = Modifier.width(40.dp))
                        Box(
                            modifier = Modifier.size(35.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Canvas(
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                if (state.isXFor1) {
                                    drawCircleinDialog(circleColor)
                                } else {
                                    drawXinDialog(xColor)
                                }
                            }
                        }
                        IconButton(
                            modifier = Modifier
                                .size(40.dp),
                            onClick = {
                                canvasOnClick()
                                onSettingsEvent(
                                    SettingsEvent.SetCustomizationType(
                                        if (state.isXFor1) Customization.CIRCLECOLOR else Customization.XCOLOR,
                                    ),
                                )
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Brush,
                                contentDescription = "Color image picker",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .size(25.dp),
                            )
                        }
                    }
                }

                // Show Image toggle bellow player image if in Online Mode
                if (state.gameMode == GameMode.Online) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        IconButton(
                            modifier = Modifier
                                .size(45.dp),
                            onClick = { onEvent(GameEvent.ToggleIsXForPlayer1) },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.SwapHoriz,
                                tint = if (isDarkThemeOn) Color.Cyan else Colors.get().blue,
                                contentDescription = "Player Icon Switch",
                                modifier = Modifier
                                    .size(45.dp),
                            )
                        }
                    }
                }

                // Difficulty level selection
                if (state.gameMode == GameMode.VsCPU) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp, bottom = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(R.string.levels),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(top = 3.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
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

                // Play button, used to start game
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        onEvent(GameEvent.StartGame)
                    },
                    modifier = Modifier
                        .width(102.dp)
                        .height(45.dp)
                        .padding(top = 4.dp)
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = stringResource(id = R.string.play),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }

    // Give focus to first player text field immediately after game screen load
    DisposableEffect(Unit) {
        focusRequester.requestFocus()

        onDispose {}
    }
}

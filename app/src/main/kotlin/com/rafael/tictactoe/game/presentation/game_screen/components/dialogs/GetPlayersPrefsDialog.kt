package com.rafael.tictactoe.game.presentation.game_screen.components.dialogs

import android.view.ViewTreeObserver
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.outlined.Brush
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.CPUDifficultyLevel
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.presentation.utils.draws.drawCircleinDialog
import com.rafael.tictactoe.game.presentation.utils.draws.drawXinDialog
import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun GetPlayersPrefsDialog(
    onEvent: (GameEvent) -> Unit,
    onSettingsEvent: (SettingsEvent) -> Unit,
    state: GameState,
    settingsState: SettingsState
) {
    val view = LocalView.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current.density
    val densityHeight = (view.height / density ).dp
    val focusRequester = remember { FocusRequester() }
    var isKeyboardOpen by rememberSaveable {  mutableStateOf(false) }
    val isXFor1 = state.isXFor1
    val isCPUMode = state.isCPUMode
    val xColor by animateColorAsState(settingsState.xColor)
    val circleColor by animateColorAsState(settingsState.circleColor)
    val isDarkThemeOn = settingsState.isDarkThemeOn
    val selectedDifficultyLevel = state.selectedDifficultyLevel
    val isCPUPlayer1 = state.isCPUPlayer1
    val player1Value = if (state.localPlayer1) state.player1Name else state.player2Name
    val player2Value = if (state.localPlayer1) state.player2Name else state.player1Name
    val textField1State = if (isCPUMode) { if (isCPUPlayer1) false else true } else {true}
    val textField2State = if (isCPUMode) { if (isCPUPlayer1) true else false } else {true}
    val customTypeForP1 = if (isXFor1) Customization.XCOLOR else Customization.CIRCLECOLOR
    val customTypeForP2 = if (isXFor1) Customization.CIRCLECOLOR else Customization.XCOLOR
    val dialogHeight by animateDpAsState(if (isCPUMode) 280.dp else 215.dp)
    val maxLetterLength by rememberSaveable { mutableIntStateOf(10) }
    val CPUDifficultyLevels = remember { mutableStateListOf(0,1,2) }
    val containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(18.dp)
    val bottomPadding by animateDpAsState(
        if (isKeyboardOpen) {
            WindowInsets.ime.asPaddingValues().calculateBottomPadding().plus(8.dp)
        } else {
            (densityHeight / 2 - dialogHeight / 2)
        }
    )

    Surface(
        color = Color.Black.copy(alpha = 0.3f),
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.25.dp, Color.Gray),
                colors = CardDefaults.cardColors(
                    containerColor = containerColor
                ),
                modifier = Modifier
                    .width(380.dp)
                    .height(dialogHeight)
            ) {

                //TextFields to get player names
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 6.dp, start = 14.dp, end = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val textFieldColors = TextFieldDefaults.colors(
                        focusedContainerColor = containerColor,
                        unfocusedContainerColor = containerColor,
                        disabledContainerColor = containerColor
                    )
                    val keyboardOptions = KeyboardOptions(
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                        capitalization = KeyboardCapitalization.Words
                    )

                    TextField(
                        value = player1Value,
                        onValueChange = {
                            if (it.length <= maxLetterLength) {
                                onEvent(GameEvent.GetFirstPlayerName(it))
                            }
                        },
                        label = {
                            Text( stringResource(id = R.string.player1_name))
                        },
                        placeholder = {
                            Text( stringResource(id = if (isXFor1) R.string.player_x else R.string.player_o))
                        },
                        enabled = textField1State,
                        keyboardOptions = keyboardOptions,
                        colors = textFieldColors,
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester)
                    )
                    Card(
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = containerColor
                        ),
                        modifier = Modifier
                            .width(60.dp)
                            .height(85.dp)
                            .clip(CircleShape)
                            .clickable {
                                onEvent(GameEvent.SwitchPlayerPositions)
                           }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.SwapHoriz,
                                tint = if (isDarkThemeOn) Color.Cyan else Colors.get().blue,
                                contentDescription = "Player Position Switch",
                                modifier = Modifier
                                    .size(45.dp)
                            )
                        }
                    }
                    TextField(
                        value = player2Value,
                        onValueChange = {
                            if (it.length <= maxLetterLength) {
                                onEvent(GameEvent.GetSecondPlayerName(it))
                            }
                        },
                        label = {
                            Text( stringResource(id = R.string.player2_name))
                        },
                        placeholder = {
                            Text( stringResource(id = if (state.isXFor1) R.string.player_o else R.string.player_x))
                        },
                        enabled = textField2State,
                        keyboardOptions = keyboardOptions,
                        colors = textFieldColors,
                        modifier = Modifier.weight(1f)
                    )
                }

                //Player images preview and button to toggle them
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val canvasOnClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        onSettingsEvent(SettingsEvent.ShowColorSelectionDialog(true))
                    }
                    val canvasModifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterVertically)

                   Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable {
                                canvasOnClick()
                                onSettingsEvent(SettingsEvent.SetCustomizationType(customTypeForP1))
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Brush,
                            contentDescription = "Color image picker",
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }
                    Box(
                        modifier = canvasModifier,
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier.size(35.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                if (isXFor1) {
                                    drawXinDialog(xColor)
                                } else {
                                    drawCircleinDialog(circleColor)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Card(
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = containerColor
                        ),
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .clickable {
                                onEvent(GameEvent.ToggleIsXForPlayer1)
                            }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.SwapHoriz,
                                tint = if (isDarkThemeOn) Color.Cyan else Colors.get().blue,
                                contentDescription = "Player Icon Switch",
                                modifier = Modifier
                                    .size(45.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Box(
                        modifier = canvasModifier,
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier.size(35.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                if (isXFor1) {
                                    drawCircleinDialog(circleColor)
                                } else {
                                    drawXinDialog(xColor)
                                }
                            }
                        }
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .clickable {
                                canvasOnClick()
                                onSettingsEvent(SettingsEvent.SetCustomizationType(customTypeForP2))
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Brush,
                            contentDescription = "Color image picker",
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }
                }

                //Difficulty level selection
                if (isCPUMode) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp, bottom = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.levels),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(top = 3.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                shape = MaterialTheme.shapes.medium,
                                border = BorderStroke(1.dp, Color.Gray),
                                modifier = Modifier
                                    .width(370.dp)
                                    .height(40.dp)
                                    .clip(RoundedCornerShape(26.dp))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .width(370.dp)
                                        .height(40.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                     CPUDifficultyLevels.forEach { level ->
                                        Row(
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(40.dp)
                                                .clickable {
                                                    when (level) {
                                                        0 -> onEvent(GameEvent.SelectCPUDifficultyMode(
                                                            level = CPUDifficultyLevel.Easy,
                                                            selectedLevel = 0,
                                                            icon = Icons.Filled.SentimentSatisfied,
                                                            name = R.string.level_easy
                                                        ))
                                                        1 -> onEvent(GameEvent.SelectCPUDifficultyMode(
                                                            level = CPUDifficultyLevel.Medium,
                                                            selectedLevel = 1,
                                                            icon = Icons.Filled.SentimentNeutral,
                                                            name = R.string.level_medium
                                                        ))
                                                        2 -> onEvent(GameEvent.SelectCPUDifficultyMode(
                                                            level = CPUDifficultyLevel.Hard,
                                                            selectedLevel = 2,
                                                            icon = Icons.Filled.SentimentDissatisfied,
                                                            name = R.string.level_hard
                                                        ))
                                                    }
                                                }
                                                .background(if (level == selectedDifficultyLevel) {
                                                    MaterialTheme.colorScheme.onSecondary } else containerColor
                                                ),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            RadioButton(
                                                selected = level == selectedDifficultyLevel,
                                                onClick = {
                                                    when (level) {
                                                        0 -> onEvent(GameEvent.SelectCPUDifficultyMode(
                                                            level = CPUDifficultyLevel.Easy,
                                                            selectedLevel = 0,
                                                            icon = Icons.Filled.SentimentSatisfied,
                                                            name = R.string.level_easy
                                                        ))
                                                        1 -> onEvent(GameEvent.SelectCPUDifficultyMode(
                                                            level = CPUDifficultyLevel.Medium,
                                                            selectedLevel = 1,
                                                            icon = Icons.Filled.SentimentDissatisfied,
                                                            name = R.string.level_medium
                                                        ))
                                                        2 -> onEvent(GameEvent.SelectCPUDifficultyMode(
                                                            level = CPUDifficultyLevel.Hard,
                                                            selectedLevel = 2,
                                                            icon = Icons.Filled.SentimentDissatisfied,
                                                            name = R.string.level_hard
                                                        ))
                                                    }
                                                }
                                            )
                                            Text(
                                                textAlign = TextAlign.Start,
                                                style = MaterialTheme.typography.bodyMedium,
                                                text = when (level) {
                                                    0 -> stringResource(R.string.level_easy)
                                                    1 -> stringResource(R.string.level_medium)
                                                    2 -> stringResource(R.string.level_hard)
                                                    else -> ""
                                                }
                                            )
                                            Icon(
                                                imageVector = when (level) {
                                                    0 -> Icons.Filled.SentimentSatisfied
                                                    1 -> Icons.Filled.SentimentNeutral
                                                    2 -> Icons.Filled.SentimentDissatisfied
                                                    else -> Icons.Filled.SentimentSatisfied
                                                },
                                                tint = when (level) {
                                                    0 -> if (isDarkThemeOn) Color.Green else Color(0xFF299961)
                                                    1 -> if (isDarkThemeOn) Color.Yellow else Colors.get().burnt_yellow
                                                    2 -> Color.Red
                                                    else -> Color.Red
                                                },
                                                contentDescription = "Icon",
       		            	                 modifier = Modifier
                                                    .size(32.dp)
                                                    .padding(end = 3.dp)
                                            )
                                        }
                                        VerticalDivider(
                                            thickness = 1.5.dp,
                                            color = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                //Play button, used to start game
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        onEvent(GameEvent.StartGame(player1Value, player2Value))
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
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    //Detect when keyboard is open
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener { 
            isKeyboardOpen = ViewCompat.getRootWindowInsets(view)?.isVisible(
                WindowInsetsCompat.Type.ime()
            ) ?: false
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose { 
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    //Give focus to firdt plaher text field immediately after game screen load
    DisposableEffect(Unit) {
        focusRequester.requestFocus()

        onDispose{}
    }
}
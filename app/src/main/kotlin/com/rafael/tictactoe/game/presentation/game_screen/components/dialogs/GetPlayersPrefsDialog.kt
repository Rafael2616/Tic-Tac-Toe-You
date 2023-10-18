package com.rafael.tictactoe.game.presentation.game_screen.components.dialogs

import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentSatisfied
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
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.card_grid.EasyModeCPUCardClick
import com.rafael.tictactoe.game.presentation.utils.draws.drawCircleinDialog
import com.rafael.tictactoe.game.presentation.utils.draws.drawXinDialog
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun GetPlayersPrefsDialog() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val view = LocalView.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current.density
    val densityHeight = (view.height / density ).dp
    var isKeyboardOpen by rememberSaveable {  mutableStateOf(false) }
    val isXFor1 by viewModel.isXFor1.collectAsState()
    val player1Name by viewModel.player1Name.collectAsState()
    val player2Name by viewModel.player2Name.collectAsState()
    val localPlayer1 by viewModel.localPlayer1.collectAsState()
    val isCPUPlayer1 by viewModel.isCPUPlayer1.collectAsState()
    val isCPUMode by viewModel.isCPUMode.collectAsState()
    val xColor by settingsVM.xColor.collectAsState()
    val circleColor by settingsVM.circleColor.collectAsState()
    val isDarkThemeOn by settingsVM.isDarkThemeOn.collectAsState()
    val selectedDifficultyMode by viewModel.selectedDifficultyMode.collectAsState()
    val player1Value = if (localPlayer1) player1Name else player2Name
    val player2Value = if (localPlayer1) player2Name else player1Name
    val textField1State = if (isCPUMode) { if (isCPUPlayer1) true else false } else {false}
    val textField2State = if (isCPUMode) { if (isCPUPlayer1) false else true } else {false}
    val customTypeforP1 = if (isXFor1) Customization.XCOLOR else Customization.CIRCLECOLOR
    val customTypeForP2 = if (isXFor1) Customization.CIRCLECOLOR else Customization.XCOLOR
    val dialogHeight by animateDpAsState(if (isCPUMode) 280.dp else 215.dp)
    val maxLetterLength by rememberSaveable { mutableIntStateOf(10) }
    val difficultyModesNumber = remember { mutableStateListOf(0,1) }
    val containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(20.dp)    
    val keyboardHeight by animateDpAsState(
        if (isKeyboardOpen) {
            WindowInsets.ime.asPaddingValues().calculateBottomPadding() + 8.dp
        } else {
            (densityHeight / 2 - dialogHeight / 2)
        }
    )

    Surface(
        color = Color.Black.copy(alpha = 0.3f),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = keyboardHeight),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.5.dp, Color.Gray),
                colors = CardDefaults.cardColors(
                    containerColor = containerColor
                ),
                modifier = Modifier
                    .width(375.dp)
                    .height(dialogHeight)
            ) {

                //TextFields to get player names
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 6.dp, start = 14.dp, end = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        value = player1Value,
                        onValueChange = {
                            if (it.length <= maxLetterLength) {
                                if (localPlayer1) viewModel.updatePlayer1Name(it) else viewModel.updatePlayer2Name(it)
                            }
                        },
                        label = {
                            Text( stringResource(id = R.string.player1_name))
                        },
                        placeholder = {
                            Text( stringResource(id = if (isXFor1) R.string.player_x else R.string.player_o))
                        },
                        readOnly = textField1State,
                        enabled = !textField1State,
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                            capitalization = KeyboardCapitalization.Words
                        ),
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = containerColor,
                            unfocusedContainerColor = containerColor,
                            disabledContainerColor = containerColor
                        )
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
                            .clickable(onClick = {
                                settingsVM.execUIClickSound()
                                if (!isCPUMode) {
                                    viewModel.updateLocalPlayer1(!localPlayer1)
                                } else {
                                    viewModel.updateIsCPUPlayer1(!isCPUPlayer1)
                               }
                           })
                    ) {
                        Image(
                            contentDescription = "Switch Player Position",
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(R.drawable.switch_icon)
                        )
                    }
                    TextField(
                        value = player2Value,
                        onValueChange = {
                            if (it.length <= maxLetterLength) {
                                if (localPlayer1) viewModel.updatePlayer2Name(it) else viewModel.updatePlayer1Name(it)
                            }
                        },
                        label = {
                            Text( stringResource(id = R.string.player2_name))
                        },
                        placeholder = {
                            Text( stringResource(id = if (isXFor1) R.string.player_o else R.string.player_x))
                        },
                        readOnly = textField2State,
                        enabled = !textField2State,
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                            capitalization = KeyboardCapitalization.Words
                        ),
                        modifier = Modifier.weight(1f),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = containerColor,
                            unfocusedContainerColor = containerColor,
                            disabledContainerColor = containerColor
                        )
                    )
                }

                //Images selected by players and button to switch this images 
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Canvas(
                        modifier = Modifier
                            .weight(1f)
                            .width(80.dp)
                            .height(60.dp)
                            .align(Alignment.CenterVertically)
                            .clickable( onClick = {
                                settingsVM.execUIClickSound()
                                keyboardController?.hide()
                                settingsVM.setCustomizationType(customTypeforP1)
                                settingsVM.showColorSelectionDialog(true)
                           })
                    ) {
                        if (isXFor1) {
                            drawXinDialog(xColor)
                        } else {
                            drawCircleinDialog(circleColor)
                        }
                    }
                    Card(
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(
                            containerColor = containerColor
                        ),
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .clickable(onClick = {
                                settingsVM.execUIClickSound()
                                viewModel.updateIsXFor1(!isXFor1)
                            })
                    ) {
                        Image(
                            contentDescription = "Player Image Switcher",
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(R.drawable.switch_icon)
                        )
                    }
                    Canvas(
                        modifier = Modifier
                            .weight(1f)
                            .width(80.dp)
                            .height(60.dp)
                            .align(Alignment.CenterVertically)
                            .clickable( onClick = {
                                settingsVM.execUIClickSound()
                                keyboardController?.hide()
                                settingsVM.setCustomizationType(customTypeForP2)
                                settingsVM.showColorSelectionDialog(true)
                            })
                    ) {
                        if (isXFor1) {
                            drawCircleinDialog(circleColor)
                        } else {
                            drawXinDialog(xColor)
                        }
                    }
                }

                //Difficulty mode selection
                if (isCPUMode) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp, bottom = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.modes),
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
                            difficultyModesNumber.forEach { mode ->
                                Card(
                                    shape = MaterialTheme.shapes.medium,
                                    border = BorderStroke(1.5.dp, Color.Gray),
                                    colors = CardDefaults.cardColors(
                                        containerColor = containerColor
                                    ),
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(40.dp)
                                        .clickable(onClick = {
                                            /*vm.updateDifficultyMode(mode)*/
                                            when (mode) {
                                                1 -> Toast.makeText(
                                                    context,
                                                    "not yet developed :(",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        })
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(40.dp)
                                            .padding(end = 6.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = mode == selectedDifficultyMode,
                                            onClick = {
                                                /*vm.updateDifficultyMode(mode)*/
                                                when (mode) {
                                                    1 -> Toast.makeText(
                                                        context,
                                                        "not yet developed :(",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        )
                                        Text(
                                            textAlign = TextAlign.Center,
                                            text = when (mode) {
                                                0 -> stringResource(R.string.mode_easy)
                                                1 -> stringResource(R.string.mode_hard)
                                                else -> ""
                                            }
                                        )
                                        Icon(
                                            imageVector = when (mode) {
                                                0 -> Icons.Filled.SentimentSatisfied
                                                1 -> Icons.Filled.SentimentDissatisfied
                                                else -> Icons.Filled.SentimentSatisfied
                                            },
                                            tint = when (mode) {
                                                0 -> if (isDarkThemeOn) Color.Green else Color(0xFF299961)
                                                1 -> Color.Red
                                                else -> Color.Red
                                            },
                                            contentDescription = "Icon",
       		            	             modifier = Modifier.size(33.dp)
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
                        settingsVM.execUIClickSound()
                        viewModel.play()
                        viewModel.updatePlayer1Name(player1Value)
                        viewModel.updatePlayer2Name(player2Value)
                        if (isCPUMode && isCPUPlayer1) {
                            viewModel.updateIsCPU(true)
                            EasyModeCPUCardClick(viewModel)
                        }
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
}
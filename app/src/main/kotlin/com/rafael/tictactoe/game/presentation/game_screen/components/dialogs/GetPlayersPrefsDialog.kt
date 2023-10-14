package com.rafael.tictactoe.game.presentation.game_screen.components.dialogs

import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.rafael.tictactoe.game.domain.util.PlayersImagesSetter
import com.rafael.tictactoe.game.domain.util.RandomCardSorter
import com.rafael.tictactoe.game.presentation.utils.draws.drawCircleinDialog
import com.rafael.tictactoe.game.presentation.utils.draws.drawXinDialog
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalComposeUiApi
fun GetPlayersPrefsDialog() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val view = LocalView.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var isKeyboardOpen by rememberSaveable {  mutableStateOf(false) }
    val isXFor1 by viewModel.isXFor1.collectAsState()
    val player1Name by viewModel.player1Name.collectAsState()
    val player2Name by viewModel.player2Name.collectAsState()
    val localPlayer1 by viewModel.localPlayer1.collectAsState()
    val isCPUPlayer1 by viewModel.isCPUPlayer1.collectAsState()
    val isCPUMode by viewModel.isCPUMode.collectAsState()
    val colorX by settingsVM.colorX.collectAsState()
    val isDarkThemeOn by settingsVM.isDarkThemeOn.collectAsState()
    val colorCircle by settingsVM.colorCircle.collectAsState()
    val selectedDifficultyMode by viewModel.selectedDifficultyMode.collectAsState()
    val player1Value = if (localPlayer1) player1Name else player2Name
    val player2Value = if (localPlayer1) player2Name else player1Name
    val textField1State = if (isCPUMode) { if (isCPUPlayer1) true else false } else {false}
    val textField2State = if (isCPUMode) { if (isCPUPlayer1) false else true } else {false}
    val type1 = if (isXFor1) Customization.COLORX else Customization.COLORCIRCLE
    val type2 = if (isXFor1) Customization.COLORCIRCLE else Customization.COLORX
    val bottomPadding = if (isKeyboardOpen) { if (isCPUMode) 205.dp else 150.dp} else {0.dp}
    val dialogHeigth = if (isCPUMode) 285.dp else 220.dp
    val maxLength = 10
    val modesSize = listOf(0,1)

    Surface(
        color = Color.Black.copy(alpha = 0.3f),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding)
        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.5.dp, Color.Gray),
                modifier = Modifier
                    .width(375.dp)
                    .height(dialogHeigth)
                    .align(Alignment.Center)
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
                            if (it.length <= maxLength) {
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
                        modifier = Modifier.weight(1f)
                    )
                    Card(
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .width(60.dp)
                            .height(85.dp)
                            .clip(CircleShape)
                            .clickable(onClick = {
                                settingsVM.execUIClickSound()
                                PlayersImagesSetter(viewModel)
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
                            if (it.length <= maxLength) {
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
                        modifier = Modifier.weight(1f)
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
                                settingsVM.setCustomizationType(type1)
                                settingsVM.showLineColorSelectorDialog(true)
                           })
                    ) {
                        if (isXFor1) {
                            drawXinDialog(colorX)
                        } else {
                            drawCircleinDialog(colorCircle)
                        }
                    }
                    Card(
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .clickable(onClick = {
                                settingsVM.execUIClickSound()
                                viewModel.updateIsXFor1(!isXFor1)
    	                        PlayersImagesSetter(viewModel)
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
                                settingsVM.setCustomizationType(type2)
                                settingsVM.showLineColorSelectorDialog(true)
                            })
                    ) {
                        if (isXFor1) {
                            drawCircleinDialog(colorCircle)
                        } else {
                            drawXinDialog(colorX)
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
                            modesSize.forEach { mode ->
                                Card(
                                    shape = MaterialTheme.shapes.medium,
                                    border = BorderStroke(1.5.dp, Color.Gray),
                                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
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
                        viewModel.updatePlay(true)
                        viewModel.updateIsXImage(isXFor1)
                        viewModel.updatePlayer1Name(player1Value)
                        viewModel.updatePlayer2Name(player2Value)
                        viewModel.updateIsTimerOn(true)
                        if (isCPUMode && isCPUPlayer1) {
                            viewModel.updateIsCPU(true)
                            RandomCardSorter(viewModel, context)
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
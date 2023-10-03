package com.rafael.tictactoe.game.presentation.game_screen.components

import android.view.ViewTreeObserver
import androidx.core.view.ViewCompat 
import androidx.core.view.WindowInsetsCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
 
@Composable
fun GetPlayerPrefsDialog() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val view = LocalView.current
    val isXFor1 by viewModel.isXFor1.collectAsState()
    val player1Name by viewModel.player1Name.collectAsState()
    val player2Name by viewModel.player2Name.collectAsState()
    val maxLength = 10
    val localPlayer1 by viewModel.localPlayer1.collectAsState()
    val isCPUPlayer1 by viewModel.isCPUPlayer1.collectAsState()
    var player1Value = if (localPlayer1) player1Name else player2Name
    var player2Value = if (localPlayer1) player2Name else player1Name
    val isCPUMode by viewModel.isCPUMode.collectAsState()
    var isKeyboardOpen by rememberSaveable {  mutableStateOf(false) }
    val bottomPadding = if (isKeyboardOpen) 160.dp else 0.dp
    val textField1State = if (isCPUMode) { if (isCPUPlayer1) true else false } else {false}
    val textField2State = if (isCPUMode) { if (isCPUPlayer1) false else true } else {false}
    
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
                    .width(380.dp)
                    .height(240.dp)
                    .align(Alignment.Center)
            ) {

                //TextFields to get player names
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
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
                                settingsVM.execUIClickSound(context)
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
                    Image(
                        contentDescription = "Player 1 Image",
                        painter = painterResource(if (isXFor1) R.drawable.x_image else R.drawable.circle_image),
                        modifier = Modifier
                            .width(120.dp)
                            .height(60.dp)
                    )
                    Card(
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .clickable(onClick = {
                                settingsVM.execUIClickSound(context)
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
                    Image(
                        contentDescription = "Second Player Image",
                        painter = painterResource(if (isXFor1) R.drawable.circle_image else R.drawable.x_image),
                        modifier = Modifier
                            .width(120.dp)
                            .height(60.dp)
                    )
                }

                //Play button, used to start game
                Button(
                    onClick = {
                        settingsVM.execUIClickSound(context)
                        viewModel.updatePlay(true)
                        viewModel.updateIsXImage(isXFor1)
                        viewModel.updatePlayer1Name(player1Value)
                        viewModel.updatePlayer2Name(player2Value)
                        viewModel.updateIsTimerOn(true)
                        if (isCPUMode) {
                            if (isCPUPlayer1) {
                                viewModel.updateIsCPU(true)
                                RandomCardSorter(viewModel, context)
                            }
                        }
                    },
                    modifier = Modifier
                        .width(100.dp)
                        .height(45.dp)
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
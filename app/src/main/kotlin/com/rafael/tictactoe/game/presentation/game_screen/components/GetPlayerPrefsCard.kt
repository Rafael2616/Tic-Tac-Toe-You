package com.rafael.tictactoe.game.presentation.components

import com.rafael.tictactoe.R
import android.content.Context
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.painter.*
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.domain.util.SetPlayerImagePref
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.launch

@Composable
fun GetPlayerPrefsCard(
    context: Context,
    settingsRepo: SettingsRepository,
    repository: GameRepository
) {
    val scope = rememberCoroutineScope()

    var isXFor1 by remember { mutableStateOf(false) }
    var player1Name by remember { mutableStateOf("") }
    var player2Name by remember { mutableStateOf("") }
    var isPlayer1 by remember { mutableStateOf(true) }
    var player1InToast by remember { mutableStateOf(true) }

    val maxLength = 10
    var player1Value = if (player1InToast) player1Name else player2Name
    var player2Value = if (player1InToast) player2Name else player1Name

    LaunchedEffect(Unit) {
        isXFor1 = repository.getIsXFor1()
        player1Name = repository.getPlayer1Name()
        player2Name = repository.getPlayer2Name()
        isPlayer1 = repository.getIsPlayer1()
        player1InToast = repository.getPlayer1InToast()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            border = BorderStroke(1.5.dp, Color.Gray),
            modifier = Modifier
                .width(380.dp)
                .height(240.dp)
                .align(Alignment.Center)
        ) {
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
                            if (isPlayer1) player1Name = it else player2Name = it
                        }
                    },
                    label = { Text(stringResource(id = R.string.player1_name)) },
                    placeholder = { Text(stringResource(id = if (isXFor1) R.string.player_x else R.string.player_o)) },
                    modifier = Modifier
                        .weight(1f)
                )
                Card(
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .width(60.dp)
                        .height(85.dp)
                        .clickable(onClick = {
                            UiClickSound(context, settingsRepo)
                            scope.launch {
                                SetPlayerImagePref(repository)
                                repository.setIsPlayer1(!isPlayer1)
                            }
                        })
                ) {
                    Image(
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.switch_icon)
                    )
                }
                TextField(
                    value = player2Value,
                    onValueChange = {
                        if (it.length <= maxLength) {
                            if (isPlayer1) player2Name = it else player1Name = it
                        }
                    },
                    label = { Text(stringResource(id = R.string.player2_name)) },
                    placeholder = { Text(stringResource(id = if (isXFor1) R.string.player_o else R.string.player_x)) },
                    modifier = Modifier
                        .weight(1f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    contentDescription = null,
                    painter = painterResource(if (isXFor1) R.drawable.x_image else R.drawable.circle_image),
                    modifier = Modifier
                        .width(120.dp)
                        .height(60.dp)
                )
                Card(
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                        .clickable(onClick = {
                            UiClickSound(context, settingsRepo)
                            scope.launch {
                                SetPlayerImagePref(repository)
                                repository.setIsXFor1(!isXFor1)
                            }
                        })
                ) {
                    Image(
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(R.drawable.switch_icon)
                    )
                }
                Image(
                    contentDescription = null,
                    painter = painterResource(if (isXFor1) R.drawable.circle_image else R.drawable.circle_image),
                    modifier = Modifier
                        .width(120.dp)
                        .height(60.dp)
                )
            }

            Button(
                onClick = {
                    UiClickSound(context, settingsRepo)
                    scope.launch { repository.setPlay(true) }
                },
                modifier = Modifier
                    .width(100.dp)
                    .height(45.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.play),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

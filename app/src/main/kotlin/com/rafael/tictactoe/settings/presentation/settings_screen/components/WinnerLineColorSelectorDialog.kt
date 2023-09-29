package com.rafael.tictactoe.settings.presentation.settings_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun WinnerLineColorSelectorDialog() {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    var optionsSize = listOf(0,1,2,3,4,5,6)
    val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()
    val selectedColorOption by settingsVM.selectedColorOption.collectAsState()
        
    Surface(
        color = Color.Black.copy(alpha = 0.3f),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.5.dp, Color.Gray),
                modifier = Modifier
                    .width(270.dp)
                    .height(325.dp)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = stringResource(R.string.choose_color),
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                    .padding(8.dp)
                    .padding(start = 10.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(6.dp)
                        .animateContentSize(),
                ) {
                    items(optionsSize.size) { option ->
                        Row(
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            RadioButton(
                                selected = option == selectedColorOption,
                                onClick = { settingsVM.updateSelectedColorOption(option);
                                    when (option) {
                                        0 -> settingsVM.setWinnerLineColor(Color.Blue)
                                        1 -> settingsVM.setWinnerLineColor(Color.Red)
                                        2 -> settingsVM.setWinnerLineColor(Color.Yellow)
                                        3 -> settingsVM.setWinnerLineColor(Color.Green)
                                        4 -> settingsVM.setWinnerLineColor(Color(0xFF6400EE))
                                        5 -> settingsVM.setWinnerLineColor(Color(0xFFB010B8))
                                        6 -> settingsVM.setWinnerLineColor(Color.White)
                                        else -> settingsVM.setWinnerLineColor(Color.Transparent)
                                    }
                                },
                            )
                            Text(
                                text = when (option) {
                                    0 -> stringResource(R.string.color_blue)
                                    1 -> stringResource(R.string.color_red)
                                    2 -> stringResource(R.string.color_yellow)
                                    3 -> stringResource(R.string.color_green)
                                    4 -> stringResource(R.string.color_purple)
                                    5 -> stringResource(R.string.color_pink)
                                    6 -> stringResource(R.string.color_white)
                                    else -> "undefined_color"
                                }
                            )
                            Box(
                                modifier = Modifier
                                    .size(55.dp)
                                    .padding(5.dp)
                                    .clip(CircleShape),
                                content = {
                                    Box(
                                        modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            when (option) {
                                                0 -> Color.Blue
                                                1 -> Color.Red
                                                2 -> Color.Yellow
                                                3 -> Color.Green
                                                4 -> Color(0xFF6400EE)
                                                5 -> Color(0xFFB010B8)
                                                6 -> Color.White
                                                else -> Color.Transparent
                                            }
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
                OutlinedButton(
                    onClick = {
                        UiClickSound(context, isSoundEffectsOn)
                        settingsVM.showWinnerLineColorDialog(false)
                    },
                    modifier = Modifier
                        .width(90.dp)
                        .height(40.dp)
                        .padding(end = 12.dp, bottom  = 6.dp)
                        .align(Alignment.End),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ok",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}
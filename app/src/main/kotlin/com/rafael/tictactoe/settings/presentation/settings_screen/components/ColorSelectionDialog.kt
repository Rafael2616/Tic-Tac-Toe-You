package com.rafael.tictactoe.settings.presentation.settings_screen.components

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.domain.use_cases.colorSelectionUseCase
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun ColorSelectionDialog() {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val colorsOptions = remember { mutableStateListOf(0,1,2,3,4,5,6,7,8) }
    val type by settingsVM.customizationType.collectAsState()
    val selectedWinnerLineColor by settingsVM.selectedWinnerLineColor.collectAsState()
    val selectedCircleColor by settingsVM.selectedCircleColor.collectAsState()
    val selectedXColor by settingsVM.selectedXColor.collectAsState()
    val colors = Colors.get()

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
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(20.dp)
                ),
                modifier = Modifier
                    .width(275.dp)
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
                ) {
                    items(colorsOptions.size) { color ->
                        Box(
                            modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                            .clickable(onClick = {
                                when (type) {
                                    Customization.WINNERLINECOLOR -> settingsVM.updateSelectedWinnerLineColor(color)
                                    Customization.CIRCLECOLOR ->  settingsVM.updateSelectedCircleColor(color)
                                    Customization.XCOLOR ->  settingsVM.updateSelectedXColor(color)
                                }
                                settingsVM.execUIClickSound()
                                colorSelectionUseCase(
                                    type,
                                    color,
                                    settingsVM
                                )
                           })
                        ) {
                            Row(
                                modifier = Modifier
                                    .height(60.dp)
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                RadioButton(
                                    selected =
                                        when (type) {
                                            Customization.WINNERLINECOLOR -> color == selectedWinnerLineColor
                                            Customization.CIRCLECOLOR -> color == selectedCircleColor
                                            Customization.XCOLOR -> color == selectedXColor
                                        },
                                    onClick = {
                                        when (type) {
                                            Customization.WINNERLINECOLOR -> settingsVM.updateSelectedWinnerLineColor(color)
                                            Customization.CIRCLECOLOR ->  settingsVM.updateSelectedCircleColor(color)
                                            Customization.XCOLOR ->  settingsVM.updateSelectedXColor(color)
                                        }
                                        settingsVM.execUIClickSound()
                                        colorSelectionUseCase(
                                            type,
                                            color,
                                            settingsVM
                                        )
                                    },
                                )
                                Text(
                                    text = when (color) {
                                        0 -> stringResource(R.string.color_blue)
                                        1 -> stringResource(R.string.color_red)
                                        2 -> stringResource(R.string.color_yellow)
                                        3 -> stringResource(R.string.color_dark_green)                                       
                                        4 -> stringResource(R.string.color_green)
                                        5 -> stringResource(R.string.color_purple)
                                        6 -> stringResource(R.string.color_pink)
                                        7 -> stringResource(R.string.color_white)
                                        8 -> stringResource(R.string.color_cyan)
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
                                                when (color) {
                                                    0 -> colors.blue
                                                    1 -> colors.red
                                                    2 -> colors.yellow
                                                    3 -> colors.dark_green
                                                    4 -> colors.light_green
                                                    5 -> colors.purple
                                                    6 -> colors.magenta
                                                    7 -> colors.white
                                                    8 -> colors.cyan
                                                    else -> colors.transparent
                                                }
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
                OutlinedButton(
                    onClick = {
                        settingsVM.execUIClickSound()
                        settingsVM.showColorSelectionDialog(false)
                    },
                    modifier = Modifier
                        .width(90.dp)
                        .height(42.dp)
                        .padding(end = 12.dp, bottom  = 6.dp)
                        .align(Alignment.End),
                ) {
                    Text(
                        text = "Ok",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
package com.rafael.tictactoe.settings.presentation.settings_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.utils.draws.drawCircleinColorDialog
import com.rafael.tictactoe.game.presentation.utils.draws.drawXinColorDialog
import com.rafael.tictactoe.game.presentation.utils.draws.drawWinnerLineinColorDialog
import com.rafael.tictactoe.settings.domain.enums.Colors
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.domain.use_cases.colorSelectionUseCase
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun ColorSelectionDialog(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState
) {
    val colorsOptions = remember { mutableStateListOf(0,1,2,3,4,5,6,7,8,9) }
    val type = state.customizationType
    val selectedWinnerLineColor = state.selectedWinnerLineColor
    val selectedCircleColor = state.selectedCircleColor
    val selectedXColor = state.selectedXColor
    val colors = Colors.get()
    val xColor by animateColorAsState(state.xColor)
    val circleColor by animateColorAsState(state.circleColor)
    val winnerLineColor by animateColorAsState(state.winnerLineColor)

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
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(1.25.dp, Color.Gray),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(20.dp)
                ),
                modifier = Modifier
                    .width(275.dp)
                    .height(325.dp)
                    .align(Alignment.Center)
            ) {
                Row(
                    modifier = Modifier.height(40.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.choose_color),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Light
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Spacer(
                        modifier = Modifier.width(15.dp)
                    )
                    Box(
                        modifier = Modifier.size(40.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .size(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Canvas(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                when (type) {
                                    Customization.XCOLOR -> {
                                        drawXinColorDialog(xColor)
                                    }
                                    Customization.CIRCLECOLOR -> {
                                        drawCircleinColorDialog(circleColor)
                                    }
                                    Customization.WINNERLINECOLOR -> {
                                        drawWinnerLineinColorDialog(winnerLineColor)
                                    }
                                }
                            }
                        }
                    }
                }
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
                                .clickable {
                                    when (type) {
                                        Customization.WINNERLINECOLOR -> onEvent(SettingsEvent.SelectedWinnerLineColor(color))
                                        Customization.CIRCLECOLOR ->  onEvent(SettingsEvent.SelectedCircleColor(color))
                                        Customization.XCOLOR ->  onEvent(SettingsEvent.SelectedXColor(color))
                                    }
                                    colorSelectionUseCase(
                                        type,
                                        color,
                                        onEvent
                                    )
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .height(60.dp)
                                    .fillMaxWidth()
                                    .padding(8.dp),
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
                                            Customization.WINNERLINECOLOR -> onEvent(SettingsEvent.SelectedWinnerLineColor(color))
                                            Customization.CIRCLECOLOR ->  onEvent(SettingsEvent.SelectedCircleColor(color))
                                            Customization.XCOLOR ->  onEvent(SettingsEvent.SelectedXColor(color))
                                        }
                                        colorSelectionUseCase(
                                            type,
                                            color,
                                            onEvent
                                        )
                                    },
                                )
                                Text(
                                    text = when (color) {
                                        0 -> stringResource(R.string.color_blue)
                                        1 -> stringResource(R.string.color_red)
                                        2 -> stringResource(R.string.color_yellow)
                                        3 -> stringResource(R.string.color_burnt_yellow)
                                        4 -> stringResource(R.string.color_dark_green)                                       
                                        5 -> stringResource(R.string.color_green)
                                        6 -> stringResource(R.string.color_purple)
                                        7 -> stringResource(R.string.color_pink)
                                        8 -> stringResource(R.string.color_white)
                                        9 -> stringResource(R.string.color_cyan)
                                        else -> "undefined_color"
                                    },
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.width(135.dp)
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
                                                    3 -> colors.burnt_yellow
                                                    4 -> colors.dark_green
                                                    5 -> colors.light_green
                                                    6 -> colors.purple
                                                    7 -> colors.magenta
                                                    8 -> colors.white
                                                    9 -> colors.cyan
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
                        onEvent(SettingsEvent.ShowColorSelectionDialog(false))
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
package com.rafael.tictactoe.settings.settingsScreen.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.gameScreen.presentation.util.draws.drawCircleinColorDialog
import com.rafael.tictactoe.game.gameScreen.presentation.util.draws.drawWinnerLineinColorDialog
import com.rafael.tictactoe.game.gameScreen.presentation.util.draws.drawXinColorDialog
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.ColorItem
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Customization
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsScreen.domain.useCases.colorSelectionUseCase
import my.nanihadesuka.compose.LazyColumnScrollbar

@Composable
@Suppress("ktlint:compose:modifier-not-used-at-root")
fun ColorSelectionDialog(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
    modifier: Modifier = Modifier,
) {
    val type = state.customizationType
    val selectedWinnerLineColor = state.selectedWinnerLineColor
    val selectedCircleColor = state.selectedCircleColor
    val selectedXColor = state.selectedXColor
    val colorItems = ColorItem.getColorItems()
    val xColor by animateColorAsState(state.xColor)
    val circleColor by animateColorAsState(state.circleColor)
    val winnerLineColor by animateColorAsState(state.winnerLineColor)
    val listState = rememberLazyListState()

    Dialog(
        onDismissRequest = {
            onEvent(SettingsEvent.ShowColorSelectionDialog(false))
        },
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(20.dp),
            ),
            modifier = modifier
                .width(280.dp)
                .height(370.dp)
                .padding(vertical = 6.dp),
        ) {
            Row(
                modifier = Modifier
                    .height(46.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterHorizontally),
            ) {
                Text(
                    text = stringResource(R.string.choose_color),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Light,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Box(
                    modifier = Modifier
                        .size(20.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        when (type) {
                            Customization.XCOLOR -> drawXinColorDialog(xColor)
                            Customization.CIRCLECOLOR -> drawCircleinColorDialog(circleColor)
                            Customization.WINNERLINECOLOR -> drawWinnerLineinColorDialog(winnerLineColor)
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(265.dp),
            ) {
                LazyColumnScrollbar(
                    listState = listState,
                    thumbColor = MaterialTheme.colorScheme.primary,
                    thumbSelectedColor = MaterialTheme.colorScheme.primary,
                    hideDelayMillis = 1000,
                    padding = 6.dp,
                    thumbMinHeight = 0.05f,
                    thickness = 6.dp,
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(265.dp),
                        contentPadding = PaddingValues(8.dp),
                    ) {
                        itemsIndexed(colorItems) { index, colorItem ->
                            Row(
                                modifier = Modifier
                                    .height(50.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 2.dp)
                                    .padding(end = 4.dp)
                                    .clickable {
                                        colorSelectionUseCase(
                                            type,
                                            colorItem.color,
                                            index,
                                            onEvent,
                                        )
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                RadioButton(
                                    selected =
                                    when (type) {
                                        Customization.WINNERLINECOLOR -> index == selectedWinnerLineColor
                                        Customization.CIRCLECOLOR -> index == selectedCircleColor
                                        Customization.XCOLOR -> index == selectedXColor
                                    },
                                    onClick = {
                                        colorSelectionUseCase(
                                            type,
                                            colorItem.color,
                                            index,
                                            onEvent,
                                        )
                                    },
                                )
                                Text(
                                    text = stringResource(colorItem.name),
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier
                                        .width(135.dp),
                                )
                                Box(
                                    modifier = Modifier
                                        .height(45.dp)
                                        .width(55.dp)
                                        .padding(5.dp)
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(colorItem.color),
                                )
                            }
                        }
                    }
                }
            }
            OutlinedButton(
                onClick = {
                    onEvent(SettingsEvent.ShowColorSelectionDialog(false))
                    onEvent(SettingsEvent.ExecUIClickSound)
                },
                modifier = Modifier
                    .padding(end = 12.dp)
                    .align(Alignment.End),
            ) {
                Text(
                    text = stringResource(android.R.string.ok),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

package com.rafael.tictactoe.settings.settingsScreen.presentation.components.dialogs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.toColor
import com.rafael.tictactoe.game.gameScreen.presentation.util.draws.drawCircleinColorDialog
import com.rafael.tictactoe.game.gameScreen.presentation.util.draws.drawWinnerLineinColorDialog
import com.rafael.tictactoe.game.gameScreen.presentation.util.draws.drawXinColorDialog
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Customization
import com.rafael.tictactoe.settings.settingsScreen.domain.model.ColorItem
import com.rafael.tictactoe.settings.settingsScreen.domain.model.IconCustomization
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.smarttoolfactory.extendedcolors.parser.rememberColorParser
import my.nanihadesuka.compose.ColumnScrollbar

@Composable
@Suppress("ktlint:compose:modifier-not-used-at-root")
fun ColorSelectionDialog(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
    modifier: Modifier = Modifier,
) {
    val clipboardManager = LocalClipboardManager.current
    val selectedIcon = when (state.customizationType) {
        Customization.XCOLOR -> 0
        Customization.CIRCLECOLOR -> 1
        Customization.WINNERLINECOLOR -> 2
    }
    var selectedWinnerLineColor by remember { mutableStateOf(state.selectedWinnerLineColor) }
    var selectedCircleColor by remember { mutableStateOf(state.selectedCircleColor) }
    var selectedXColor by remember { mutableStateOf(state.selectedXColor) }
    val colorItems = ColorItem.getColorItems()
    var xColor by remember { mutableStateOf(state.xColor) }
    var circleColor by remember { mutableStateOf(state.circleColor) }
    var winnerLineColor by remember { mutableStateOf(state.winnerLineColor) }
    val colorPickerController = rememberColorPickerController()
    val scrollState = rememberScrollState()
    val colorParser = rememberColorParser()
    var localColor by remember { mutableStateOf(Color.Blue) }
    var localHexColor by remember { mutableStateOf("FFFFFFFF") }
    var isCustomColor by remember { mutableStateOf(false) }
    val customizations = listOf(
        IconCustomization(
            label = Customization.XCOLOR,
            name = R.string.color_x,
            selectedIndex = selectedXColor,
            color = xColor,
            isSelected = { it == selectedXColor },
            drawIcon = { drawXinColorDialog(xColor) },
            onSelect = { newColor ->
                xColor = newColor
            },
            onSelectIndex = { index ->
                selectedXColor = index
                xColor = index.toColor()
            },
            onApply = {
                onEvent(SettingsEvent.SetXColor(xColor))
                onEvent(SettingsEvent.SelectedXColor(selectedXColor))
            },
        ),
        IconCustomization(
            label = Customization.CIRCLECOLOR,
            name = R.string.color_circle,
            selectedIndex = selectedCircleColor,
            color = circleColor,
            isSelected = { it == selectedCircleColor },
            drawIcon = { drawCircleinColorDialog(circleColor) },
            onSelect = { newColor ->
                circleColor = newColor
            },
            onSelectIndex = { index ->
                selectedCircleColor = index
                circleColor = index.toColor()
            },
            onApply = {
                onEvent(SettingsEvent.SetCircleColor(circleColor))
                onEvent(SettingsEvent.SelectedCircleColor(selectedCircleColor))
            },
        ),
        IconCustomization(
            label = Customization.WINNERLINECOLOR,
            name = R.string.winner_line_color,
            selectedIndex = selectedWinnerLineColor,
            color = winnerLineColor,
            isSelected = { it == selectedWinnerLineColor },
            drawIcon = { drawWinnerLineinColorDialog(winnerLineColor) },
            onSelect = { newColor ->
                winnerLineColor = newColor
            },
            onSelectIndex = { index ->
                selectedWinnerLineColor = index
                winnerLineColor = index.toColor()
            },
            onApply = {
                onEvent(SettingsEvent.SetWinnerLineColor(winnerLineColor))
                onEvent(SettingsEvent.SelectedWinnerLineColor(selectedWinnerLineColor))
            },
        ),
    )
    val currentIcon = customizations[selectedIcon]

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
                .width(355.dp)
                .height(535.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
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
                            .size(20.dp)
                            .clickable {
                                clipboardManager.setText(AnnotatedString(localHexColor))
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        Canvas(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            currentIcon.drawIcon(this)
                        }
                    }
                }
                Text(text = "Presets:")
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(5),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(115.dp),
                    contentPadding = PaddingValues(2.dp),
                    verticalItemSpacing = 4.dp,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    itemsIndexed(colorItems) { index, item ->
                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .then(
                                    if (!isCustomColor && currentIcon.isSelected(index)) {
                                        Modifier.border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                                    } else {
                                        Modifier
                                    },
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(item.color)
                                    .clickable {
                                        currentIcon.onSelectIndex(index)
                                        isCustomColor = false
                                        colorPickerController.selectByColor(item.color, false)
                                    },
                                contentAlignment = Alignment.Center,
                            ) {
                                if (currentIcon.isSelected(index) && !isCustomColor) {
                                    Icon(
                                        contentDescription = null,
                                        imageVector = Icons.Filled.Check,
                                    )
                                }
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .height(275.dp)
                        .fillMaxWidth(),
                ) {
                    ColumnScrollbar(
                        state = scrollState,
                        thumbColor = MaterialTheme.colorScheme.primary,
                        thumbSelectedColor = MaterialTheme.colorScheme.primary,
                        padding = 2.dp,
                        thumbMinHeight = 0.05f,
                        thickness = 6.dp,
                        alwaysShowScrollBar = true,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(275.dp)
                                .verticalScroll(scrollState),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = colorParser.parseColorName(localColor))
                            Box(
                                modifier = Modifier.size(250.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                HsvColorPicker(
                                    modifier = Modifier
                                        .size(240.dp),
                                    controller = colorPickerController,
                                    initialColor = remember { customizations[selectedIcon].color },
                                    onColorChanged = { colorEnvelope ->
                                        localColor = colorEnvelope.color
                                        localHexColor = colorEnvelope.hexCode
                                        if (colorEnvelope.fromUser) isCustomColor = true
                                        currentIcon.onSelect(colorEnvelope.color)
                                    },
                                )
                            }
                            Text(
                                text = stringResource(R.string.brightness) + ":",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                            )
                            BrightnessSlider(
                                controller = colorPickerController,
                                wheelRadius = 12.dp,
                                wheelColor = Color.Gray,
                                borderRadius = 8.dp,
                                initialColor = remember { currentIcon.color },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 16.dp)
                                    .height(25.dp),
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
                ) {
                    OutlinedButton(
                        onClick = {
                            onEvent(SettingsEvent.ShowColorSelectionDialog(false))
                            onEvent(SettingsEvent.ExecUIClickSound)
                        },
                        contentPadding = PaddingValues(horizontal = 12.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Button(
                        onClick = {
                            onEvent(SettingsEvent.ShowColorSelectionDialog(false))
                            onEvent(SettingsEvent.ExecUIClickSound)
                            currentIcon.onApply()
                        },
                        contentPadding = PaddingValues(horizontal = 12.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.apply),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

package com.rafael.tictactoe.feature.settings.presentation.components.dialogs

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
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
import com.rafael.tictactoe.core.domain.util.COLORLIST
import com.rafael.tictactoe.core.domain.util.toColor
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.apply
import com.rafael.tictactoe.core.resources.brightness
import com.rafael.tictactoe.core.resources.cancel
import com.rafael.tictactoe.core.resources.choose_color
import com.rafael.tictactoe.core.resources.color_circle
import com.rafael.tictactoe.core.resources.color_x
import com.rafael.tictactoe.core.resources.draws.drawCircleInColorDialog
import com.rafael.tictactoe.core.resources.draws.drawWinnerLineInColorDialog
import com.rafael.tictactoe.core.resources.draws.drawXinColorDialog
import com.rafael.tictactoe.core.resources.presets
import com.rafael.tictactoe.core.resources.winner_line_color
import com.rafael.tictactoe.feature.settings.domain.enums.Customization
import com.rafael.tictactoe.feature.settings.domain.model.ColorItem
import com.rafael.tictactoe.feature.settings.domain.model.IconCustomization
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.smarttoolfactory.extendedcolors.parser.rememberColorParser
import my.nanihadesuka.compose.ColumnScrollbar
import my.nanihadesuka.compose.ScrollbarSettings
import org.jetbrains.compose.resources.stringResource

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
    var selectedWinnerLineColor by remember { mutableIntStateOf(state.selectedWinnerLineColor) }
    var selectedCircleColor by remember { mutableIntStateOf(state.selectedCircleColor) }
    var selectedXColor by remember { mutableIntStateOf(state.selectedXColor) }
    val colorItems = ColorItem.getColorItems()
    var xColor by remember { mutableStateOf(state.xColor) }
    var circleColor by remember { mutableStateOf(state.circleColor) }
    var winnerLineColor by remember { mutableStateOf(state.winnerLineColor) }
    val colorPickerController = rememberColorPickerController()
    val scrollState = rememberScrollState()
    val colorParser = rememberColorParser()
    var localColor by remember { mutableStateOf(Color.Blue) }
    var localHexColor by remember { mutableStateOf("FFFFFFFF") }
    val customizations = listOf(
        IconCustomization(
            label = Customization.XCOLOR,
            name = stringResource(Res.string.color_x),
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
            name = stringResource(Res.string.color_circle),
            selectedIndex = selectedCircleColor,
            color = circleColor,
            isSelected = { it == selectedCircleColor },
            drawIcon = { drawCircleInColorDialog(circleColor) },
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
            name = stringResource(Res.string.winner_line_color),
            selectedIndex = selectedWinnerLineColor,
            color = winnerLineColor,
            isSelected = { it == selectedWinnerLineColor },
            drawIcon = { drawWinnerLineInColorDialog(winnerLineColor) },
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
    val isCustomColor = customizations[selectedIcon].color !in COLORLIST

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
                        text = stringResource(Res.string.choose_color),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Light,
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
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
                Text(text = stringResource(Res.string.presets) + ":")
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
                ColumnScrollbar(
                    state = scrollState,
                    modifier = Modifier.height(275.dp),
                    settings = ScrollbarSettings(
                        scrollbarPadding = 2.dp,
                        thumbUnselectedColor = MaterialTheme.colorScheme.primary,
                        thumbSelectedColor = MaterialTheme.colorScheme.primary,
                        thumbMinLength = 0.05f,
                        thumbThickness = 6.dp,
                        alwaysShowScrollbar = true,
                    ),
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
                                    currentIcon.onSelect(colorEnvelope.color)
                                },
                            )
                        }
                        Text(
                            text = stringResource(Res.string.brightness) + ":",
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
                Row(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
                ) {
                    OutlinedButton(
                        onClick = {
                            onEvent(SettingsEvent.ShowColorSelectionDialog(false))
                        },
                        contentPadding = PaddingValues(horizontal = 12.dp),
                    ) {
                        Text(
                            text = stringResource(Res.string.cancel),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Button(
                        onClick = {
                            onEvent(SettingsEvent.ShowColorSelectionDialog(false))
                            currentIcon.onApply()
                        },
                        contentPadding = PaddingValues(horizontal = 12.dp),
                    ) {
                        Text(
                            text = stringResource(Res.string.apply),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

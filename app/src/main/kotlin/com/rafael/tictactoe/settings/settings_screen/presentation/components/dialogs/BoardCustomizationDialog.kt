package com.rafael.tictactoe.settings.settingsScreen.presentation.components.dialogs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SettingsBackupRestore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.BoardCustomization
import com.rafael.tictactoe.settings.settingsScreen.domain.model.BoardShape
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import kotlin.math.roundToInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@Suppress("ktlint:compose:modifier-not-used-at-root")
fun BoardCustomizationDialog(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val colorPickerController = rememberColorPickerController()
    val scrollState = rememberScrollState()
    val shapeSelected = state.shapeSelected
    var sliderCornerRadius by remember { mutableStateOf(if (shapeSelected == 3) state.boardCardsCornerRadius else 12f) }
    var sliderThickness by remember { mutableStateOf(if (shapeSelected == 3) state.boardCardsThickness else 2f) }
    var sliderElevation by remember { mutableStateOf(if (shapeSelected == 3) state.boardCardsElevation else 20f) }
    var sliderShadow by remember { mutableStateOf(if (shapeSelected == 3) state.boardCardsShadow else 0f) }
    var localBoardCardsBorderColor by remember { mutableStateOf(Color.White) }
    var localShapeSelected by remember { mutableIntStateOf(1) }
    var needUpdateLocalBorderColorVar by remember { mutableStateOf(true) }

    if (needUpdateLocalBorderColorVar) {
        localBoardCardsBorderColor = state.boardCardsBorderColor
        needUpdateLocalBorderColorVar = false
    }

    val shapes = listOf(
        BoardShape(
            name = R.string.square,
            cornerRadius = 1f,
        ),
        BoardShape(
            name = R.string.rounded,
            cornerRadius = 24f,
        ),
        BoardShape(
            name = R.string.circle,
            cornerRadius = 65f,
        ),
        BoardShape(
            name = R.string.custom,
            cornerRadius = sliderCornerRadius,
            elevation = sliderElevation,
            thickness = sliderThickness,
            shadow = sliderShadow,
            borderColor = localBoardCardsBorderColor,
        ),
    )

    val customizations = listOf(
        BoardCustomization(
            name = R.string.corner_radius,
            valueRange = 0f..65f,
            value = sliderCornerRadius,
            onValueChange = { if (shapeSelected == 3) sliderCornerRadius = it },
            steps = 64,
        ),
        BoardCustomization(
            name = R.string.border_thickness,
            valueRange = 0f..5f,
            value = sliderThickness,
            onValueChange = { if (shapeSelected == 3) sliderThickness = it },
            steps = 19,
        ),
        BoardCustomization(
            name = R.string.elevation,
            valueRange = 0f..80f,
            value = sliderElevation,
            onValueChange = { if (shapeSelected == 3) sliderElevation = it },
            steps = 15,
        ),
        BoardCustomization(
            name = R.string.shadow,
            valueRange = 0f..20f,
            value = sliderShadow,
            onValueChange = { if (shapeSelected == 3) sliderShadow = it },
            steps = 18,
        ),
    )

    Dialog(
        onDismissRequest = {
            onEvent(SettingsEvent.ShowBoardCustomizationDialog(false))
        },
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(20.dp),
            ),
            modifier = modifier
                .width(430.dp),
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            ) {
                Text(
                    text = stringResource(R.string.board_customization),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                    ),
                    color = MaterialTheme.colorScheme.inverseSurface,
                    textAlign = TextAlign.Start,
                )
                LazyRow(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.surface),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                    contentPadding = PaddingValues(4.dp),
                ) {
                    itemsIndexed(shapes) { index, shape ->
                        Column(
                            modifier = Modifier.wrapContentHeight(),
                            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Card(
                                shape = RoundedCornerShape(24.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                ),
                                border = if (shapeSelected == index) BorderStroke(1.25.dp, MaterialTheme.colorScheme.primary) else null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(24.dp))
                                    .clickable {
                                        onEvent(SettingsEvent.SetBoardShape(shapes[index], index))
                                        scope.launch { listState.animateScrollToItem(index, 1) }
                                    },

                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Card(
                                        shape = RoundedCornerShape(shape.cornerRadius),
                                        border = if (index == 3 && shape.thickness != 0f) {
                                            BorderStroke(shape.thickness.dp, shape.borderColor)
                                        } else {
                                            null
                                        },
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(shape.elevation.dp),
                                        ),
                                        modifier = Modifier
                                            .size(70.dp)
                                            .then(
                                                if (shape.shadow != 0f && index == 3) {
                                                    Modifier.shadow(shape.shadow.roundToInt().dp, RoundedCornerShape(shape.cornerRadius))
                                                } else {
                                                    Modifier
                                                },
                                            ),
                                    ) { /* No content here */ }
                                }
                            }
                            Text(
                                text = stringResource(shape.name),
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (shapeSelected == 3) {
                                Modifier.height(310.dp)
                            } else {
                                Modifier.wrapContentHeight()
                            },
                        )
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (shapeSelected == 3) {
                        Spacer(Modifier.height(6.dp))
                        customizations.forEachIndexed { index, customization ->
                            Text(
                                text = stringResource(customization.name) +
                                    ": " +
                                    if (index == 1) {
                                        "%.2f".format(customization.value)
                                    } else {
                                        customization.value.roundToInt()
                                    } +
                                    " dp",
                                modifier = Modifier.align(Alignment.Start),
                            )
                            Slider(
                                modifier = Modifier.semantics { contentDescription = "slider" },
                                value = customization.value,
                                valueRange = customization.valueRange,
                                steps = customization.steps,
                                onValueChange = {
                                    customization.onValueChange(it)
                                    localShapeSelected = index
                                    scope.launch { listState.animateScrollToItem(shapeSelected, 1) }
                                },
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(text = stringResource(R.string.border_color) + ":")
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(localBoardCardsBorderColor)
                                    .size(15.dp),
                            )
                        }
                        Box(
                            modifier = Modifier.size(258.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            HsvColorPicker(
                                modifier = Modifier
                                    .padding(vertical = 12.dp)
                                    .size(250.dp),
                                controller = colorPickerController,
                                initialColor = localBoardCardsBorderColor,
                                onColorChanged = { colorEnvelope ->
                                    localBoardCardsBorderColor = colorEnvelope.color
                                },
                            )
                        }
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),

            ) {
                if (localShapeSelected == 3 && sliderCornerRadius != 12f || sliderThickness != 2f || sliderElevation != 20f || sliderShadow != 0f || localBoardCardsBorderColor != Color.White) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(24.dp))
                            .clickable {
                                onEvent(SettingsEvent.ResetBoardParams)
                                sliderCornerRadius = 12f
                                sliderThickness = 2f
                                sliderElevation = 20f
                                sliderShadow = 0f
                                localBoardCardsBorderColor = Color.White
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(70.dp),
                        ),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
                            modifier = Modifier
                                .height(40.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.SettingsBackupRestore,
                                contentDescription = "slider",
                                modifier = Modifier
                                    .size(32.dp)
                                    .padding(start = 6.dp),
                            )
                            Text(
                                text = stringResource(R.string.reset_colors),
                                modifier = Modifier
                                    .padding(end = 6.dp),
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    Button(
                        onClick = {
                            onEvent(SettingsEvent.SetBoardShape(shapes[shapeSelected], shapeSelected))
                            scope.launch {
                                // Ensure settings is saved on database
                                delay(300L)
                                onEvent(SettingsEvent.ShowBoardCustomizationDialog(false))
                            }
                        },
                    ) {
                        Text(text = stringResource(R.string.apply))
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        scope.launch {
            listState.animateScrollToItem(shapeSelected, 1)
        }
        onDispose {}
    }
}

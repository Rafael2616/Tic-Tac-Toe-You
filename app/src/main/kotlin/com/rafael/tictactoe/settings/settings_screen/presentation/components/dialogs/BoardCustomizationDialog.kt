package com.rafael.tictactoe.settings.settingsScreen.presentation.components.dialogs

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
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
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.animations.rememberRGBAnimation
import com.rafael.tictactoe.settings.settingsScreen.domain.model.BoardCustomization
import com.rafael.tictactoe.settings.settingsScreen.domain.model.BoardShape
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.smarttoolfactory.extendedcolors.parser.rememberColorParser
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
    val colorParser = rememberColorParser()
    val scrollState = rememberScrollState()
    var sliderCornerRadius by remember { mutableStateOf(state.boardCardsCornerRadius) }
    var sliderThickness by remember { mutableStateOf(state.boardCardsThickness) }
    var sliderElevation by remember { mutableStateOf(state.boardCardsElevation) }
    var sliderShadow by remember { mutableStateOf(state.boardCardsShadow) }
    val boardCardsBorderColor by animateColorAsState(state.boardCardsBorderColor)
    var localShapeSelected by remember { mutableIntStateOf(state.shapeSelected) }
    var localCardsBorderHexColor by remember { mutableStateOf("FFFFFFFF") }
    val context = LocalContext.current
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(context.getString(R.string.border_color), localCardsBorderHexColor)
    var isRGBEffectOn by remember { mutableStateOf(state.isRGBEffectOn) }
    var isGradientOn by remember { mutableStateOf(state.isGradientOn) }
    val rgbAnimController = rememberRGBAnimation()
    var rgbColorCurrent by remember { mutableStateOf(Color.Red) }
    var rgbColorOld by remember { mutableStateOf(Color.Yellow) }
    val rgbColorCurrentAnimated by animateColorAsState(rgbColorCurrent)
    val rgbColorOldAnimated by animateColorAsState(rgbColorOld)
    val shouldUseRGBEffect = remember { derivedStateOf { isRGBEffectOn && localShapeSelected == 3 } }
    var localBoardCardsBorderColor by remember(if (shouldUseRGBEffect.value) rgbColorCurrentAnimated else Unit) {
        mutableStateOf(if (shouldUseRGBEffect.value) rgbColorCurrentAnimated else boardCardsBorderColor)
    }
    val rgbGradientBrush = Brush.linearGradient(
        colors = listOf(rgbColorCurrentAnimated, rgbColorOldAnimated),
    )

    LaunchedEffect(isRGBEffectOn) {
        rgbAnimController.animate().collect { currentColor ->
            rgbColorCurrent = currentColor
            delay(125L)
            rgbColorOld = currentColor
            delay(125L)
        }
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
            borderHexColor = localCardsBorderHexColor,
        ),
    )

    val customizations = listOf(
        BoardCustomization(
            name = R.string.corner_radius,
            valueRange = 0f..65f,
            value = sliderCornerRadius,
            onValueChange = { if (localShapeSelected == 3) sliderCornerRadius = it },
            steps = 64,
        ),
        BoardCustomization(
            name = R.string.border_thickness,
            valueRange = 0f..5f,
            value = sliderThickness,
            onValueChange = { if (localShapeSelected == 3) sliderThickness = it },
            steps = 19,
        ),
        BoardCustomization(
            name = R.string.elevation,
            valueRange = 0f..80f,
            value = sliderElevation,
            onValueChange = { if (localShapeSelected == 3) sliderElevation = it },
            steps = 15,
        ),
        BoardCustomization(
            name = R.string.shadow,
            valueRange = 0f..20f,
            value = sliderShadow,
            onValueChange = { if (localShapeSelected == 3) sliderShadow = it },
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
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (localShapeSelected == 3 && sliderThickness > 0f) {
                            Modifier.height(575.dp)
                        } else {
                            Modifier.wrapContentSize()
                        },
                    )
                    .animateContentSize()
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
                                border = if (localShapeSelected == index) BorderStroke(1.25.dp, MaterialTheme.colorScheme.primary) else null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(24.dp))
                                    .clickable {
                                        localShapeSelected = index
                                        scope.launch { listState.animateScrollToItem(index, 1) }
                                    },
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Card(
                                        shape = RoundedCornerShape(shape.cornerRadius),
                                        border = if (index == 3 && shape.thickness > 0f) {
                                            if (shouldUseRGBEffect.value && isGradientOn) {
                                                BorderStroke(shape.thickness.dp, rgbGradientBrush)
                                            } else {
                                                BorderStroke(shape.thickness.dp, shape.borderColor)
                                            }
                                        } else {
                                            null
                                        },
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(shape.elevation.dp),
                                        ),
                                        modifier = Modifier
                                            .size(70.dp)
                                            .then(
                                                if (shape.shadow > 0f && index == 3) {
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
                if (localShapeSelected == 3) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                            .verticalScroll(scrollState),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
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
                                    scope.launch { listState.animateScrollToItem(localShapeSelected, 1) }
                                },
                            )
                        }
                        if (sliderThickness > 0f) {
                            Spacer(Modifier.height(6.dp))
                            HorizontalDivider()
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { isRGBEffectOn = !isRGBEffectOn },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(text = stringResource(R.string.rgb_effect))
                                Switch(
                                    checked = isRGBEffectOn,
                                    onCheckedChange = { isRGBEffectOn = it },
                                )
                            }
                            if (isRGBEffectOn) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Warning,
                                        tint = MaterialTheme.colorScheme.error,
                                        contentDescription = null,
                                    )
                                    Text(
                                        text = stringResource(R.string.overheat_warning),
                                        color = MaterialTheme.colorScheme.onErrorContainer,
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { isGradientOn = !isGradientOn },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(text = stringResource(R.string.color_gradient))
                                    Switch(
                                        checked = isGradientOn,
                                        onCheckedChange = { isGradientOn = it },
                                    )
                                }
                            }
                            HorizontalDivider()
                            Spacer(Modifier.height(6.dp))
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .animateContentSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            if (sliderThickness > 0f && !isRGBEffectOn) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(text = stringResource(R.string.border_color) + ": " + colorParser.parseColorName(localBoardCardsBorderColor))
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(localBoardCardsBorderColor)
                                            .size(15.dp)
                                            .clickable { clipboardManager.setPrimaryClip(clipData) },
                                    )
                                }
                                Box(
                                    modifier = Modifier.size(250.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    HsvColorPicker(
                                        modifier = Modifier
                                            .padding(vertical = 12.dp)
                                            .size(240.dp),
                                        controller = colorPickerController,
                                        initialColor = boardCardsBorderColor,
                                        onColorChanged = { colorEnvelope ->
                                            localBoardCardsBorderColor = colorEnvelope.color
                                            localCardsBorderHexColor = colorEnvelope.hexCode
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
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(25.dp),
                                )
                                Text(
                                    text = stringResource(R.string.alpha) + ":",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                )
                                AlphaSlider(
                                    controller = colorPickerController,
                                    wheelRadius = 12.dp,
                                    wheelColor = Color.Gray,
                                    borderRadius = 8.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(25.dp),
                                )
                            }
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
                if (localShapeSelected == 3 && (sliderCornerRadius != 12f || sliderThickness != 2f || sliderElevation != 20f || sliderShadow != 0f || localBoardCardsBorderColor != Color.White)) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(24.dp))
                            .clickable {
                                localBoardCardsBorderColor = Color.White
                                isGradientOn = true
                                isRGBEffectOn = false
                                sliderCornerRadius = 12f
                                sliderThickness = 2f
                                sliderElevation = 20f
                                sliderShadow = 0f
                                colorPickerController.selectByColor(Color.White, true)
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
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .padding(end = 6.dp),
                            )
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.End),
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    OutlinedButton(
                        onClick = {
                            onEvent(SettingsEvent.ShowBoardCustomizationDialog(false))
                        },
                        contentPadding = PaddingValues(vertical = 6.dp, horizontal = 8.dp),
                        modifier = Modifier.width(80.dp),
                    ) {
                        Text(text = stringResource(R.string.cancel))
                    }
                    Button(
                        onClick = {
                            onEvent(
                                SettingsEvent.SetBoardShape(
                                    shapes[localShapeSelected],
                                    localShapeSelected,
                                    isRGBEffectOn,
                                    isGradientOn,
                                ),
                            )
                            scope.launch {
                                // Ensure settings is saved on database
                                delay(300L)
                                onEvent(SettingsEvent.ShowBoardCustomizationDialog(false))
                            }
                        },
                        contentPadding = PaddingValues(vertical = 6.dp, horizontal = 8.dp),
                        modifier = Modifier.width(80.dp),
                    ) {
                        Text(text = stringResource(R.string.apply))
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        scope.launch {
            listState.animateScrollToItem(state.shapeSelected, 1)
        }
        onDispose {}
    }
}

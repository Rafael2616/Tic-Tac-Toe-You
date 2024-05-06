package com.rafael.tictactoe.settings.settingsScreen.presentation.components.dialogs

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
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
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
import com.rafael.tictactoe.feature.settings.R
import com.rafael.tictactoe.settings.settingsScreen.domain.animations.rememberRGBAnimation
import com.rafael.tictactoe.settings.settingsScreen.domain.model.BoardCustomization
import com.rafael.tictactoe.settings.settingsScreen.domain.model.BoardShape
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.DEF_CARDS_BORDER_THICKNESS
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.DEF_CARDS_COLOR
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.DEF_CARDS_CORNER_RADIUS
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.DEF_CARDS_ELEVATION
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.DEF_CARDS_SHADOW
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.SHAPE_CUSTOM
import com.smarttoolfactory.extendedcolors.parser.rememberColorParser
import kotlin.math.roundToInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import my.nanihadesuka.compose.LazyRowScrollbar
import my.nanihadesuka.compose.ScrollbarSettings

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
    var sliderCornerRadius by remember { mutableStateOf(if (state.shapeSelected == SHAPE_CUSTOM) state.boardCardsCornerRadius else DEF_CARDS_CORNER_RADIUS) }
    var sliderThickness by remember { mutableStateOf(if (state.shapeSelected == SHAPE_CUSTOM) state.boardCardsThickness else DEF_CARDS_BORDER_THICKNESS) }
    var sliderElevation by remember { mutableStateOf(if (state.shapeSelected == SHAPE_CUSTOM) state.boardCardsElevation else DEF_CARDS_ELEVATION) }
    var sliderShadow by remember { mutableStateOf(if (state.shapeSelected == SHAPE_CUSTOM) state.boardCardsShadow else DEF_CARDS_SHADOW) }
    var localShapeSelected by remember { mutableIntStateOf(state.shapeSelected) }
    var localCardsBorderHexColor by remember { mutableStateOf("FFFFFFFF") }
    var isInitialized by remember { mutableStateOf(false) }
    val clipboardManager = LocalClipboardManager.current
    var isRGBEffectOn by remember { mutableStateOf(state.isRGBEffectOn) }
    var isGradientOn by remember { mutableStateOf(state.isGradientOn) }
    val rgbAnimController = rememberRGBAnimation()
    var rgbColorCurrent by remember { mutableStateOf(Color.Red) }
    var rgbColorOld by remember { mutableStateOf(Color.Yellow) }
    val rgbColorCurrentAnimated by animateColorAsState(rgbColorCurrent)
    val rgbColorOldAnimated by animateColorAsState(rgbColorOld)
    var localBoardCardsBorderColor by remember { mutableStateOf(if (state.shapeSelected == SHAPE_CUSTOM) state.boardCardsBorderColor else DEF_CARDS_COLOR) }
    var localBoardCardsBorderColorRGB by remember(rgbColorCurrentAnimated) {
        mutableStateOf(rgbColorCurrentAnimated)
    }
    val rgbGradientBrush = Brush.linearGradient(
        colors = listOf(rgbColorCurrentAnimated, rgbColorOldAnimated),
    )

    LaunchedEffect(localShapeSelected == SHAPE_CUSTOM && isRGBEffectOn) {
        if (localShapeSelected == SHAPE_CUSTOM && isRGBEffectOn) {
            rgbAnimController.animate().collect { currentColor ->
                rgbColorCurrent = currentColor
                delay(125L)
                rgbColorOld = currentColor
                delay(125L)
            }
            if (!isInitialized) isInitialized = true
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
            onValueChange = { if (localShapeSelected == SHAPE_CUSTOM) sliderCornerRadius = it },
            steps = 64,
        ),
        BoardCustomization(
            name = R.string.border_thickness,
            valueRange = 0f..5f,
            value = sliderThickness,
            onValueChange = { if (localShapeSelected == SHAPE_CUSTOM) sliderThickness = it },
            steps = 19,
        ),
        BoardCustomization(
            name = R.string.elevation,
            valueRange = 0f..80f,
            value = sliderElevation,
            onValueChange = { if (localShapeSelected == SHAPE_CUSTOM) sliderElevation = it },
            steps = 15,
        ),
        BoardCustomization(
            name = R.string.shadow,
            valueRange = 0f..20f,
            value = sliderShadow,
            onValueChange = { if (localShapeSelected == SHAPE_CUSTOM) sliderShadow = it },
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
                        if (localShapeSelected == SHAPE_CUSTOM && sliderThickness > 0f) {
                            Modifier.height(580.dp)
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
                LazyRowScrollbar(
                    state = listState,
                    modifier = Modifier.height(155.dp),
                    settings = ScrollbarSettings(
                        scrollbarPadding = 2.dp,
                        thumbUnselectedColor = MaterialTheme.colorScheme.primary,
                        thumbSelectedColor = MaterialTheme.colorScheme.primary,
                        thumbMinLength = 0.05f,
                        thumbThickness = 6.dp,
                        alwaysShowScrollbar = false,
                    ),
                ) {
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
                                            isInitialized = false
                                            scope.launch { listState.animateScrollToItem(index, 1) }
                                        },
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Card(
                                            shape = RoundedCornerShape(shape.cornerRadius),
                                            border = if (shape.thickness > 0f && index == SHAPE_CUSTOM) {
                                                when {
                                                    localShapeSelected == SHAPE_CUSTOM && isRGBEffectOn && isGradientOn -> BorderStroke(shape.thickness.dp, rgbGradientBrush)
                                                    localShapeSelected == SHAPE_CUSTOM && isRGBEffectOn && !isGradientOn -> BorderStroke(shape.thickness.dp, localBoardCardsBorderColorRGB)
                                                    localShapeSelected != SHAPE_CUSTOM && isRGBEffectOn -> BorderStroke(shape.thickness.dp, Brush.linearGradient(colors = listOf(Color.Red, Color.Yellow)))
                                                    else -> BorderStroke(shape.thickness.dp, shape.borderColor)
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
                                                    if (shape.shadow > 0f && index == SHAPE_CUSTOM) {
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
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }
                }
                if (localShapeSelected == SHAPE_CUSTOM) {
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
                                    if (listState.firstVisibleItemIndex != 1) {
                                        scope.launch { listState.animateScrollToItem(localShapeSelected, 1) }
                                    }
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
                        if (!isRGBEffectOn && sliderThickness > 0f) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .animateContentSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
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
                                            .clickable { clipboardManager.setText(AnnotatedString(localCardsBorderHexColor)) },
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
                                        initialColor = remember { if (isInitialized) DEF_CARDS_COLOR else localBoardCardsBorderColor },
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
                                    initialColor = remember { if (isInitialized) DEF_CARDS_COLOR else localBoardCardsBorderColor },
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
                                    initialColor = remember { if (isInitialized) DEF_CARDS_COLOR else localBoardCardsBorderColor },
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
                if (localShapeSelected == SHAPE_CUSTOM && (sliderCornerRadius != DEF_CARDS_CORNER_RADIUS || sliderThickness != DEF_CARDS_BORDER_THICKNESS || sliderElevation != DEF_CARDS_ELEVATION || sliderShadow != DEF_CARDS_SHADOW || !isGradientOn || isRGBEffectOn || localBoardCardsBorderColor != DEF_CARDS_COLOR)) {
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(24.dp))
                            .clickable {
                                isGradientOn = true
                                isRGBEffectOn = false
                                localBoardCardsBorderColor = DEF_CARDS_COLOR
                                sliderCornerRadius = DEF_CARDS_CORNER_RADIUS
                                sliderThickness = DEF_CARDS_BORDER_THICKNESS
                                sliderElevation = DEF_CARDS_ELEVATION
                                sliderShadow = DEF_CARDS_SHADOW
                                colorPickerController.selectByColor(DEF_CARDS_COLOR, true)
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

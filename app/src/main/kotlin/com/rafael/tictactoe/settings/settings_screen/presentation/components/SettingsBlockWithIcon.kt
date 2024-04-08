package com.rafael.tictactoe.settings.settingsScreen.presentation.components

import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.getVersionCode
import com.rafael.tictactoe.common.domain.util.getVersionName
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.SettingsGroup
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItem
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsScreen.domain.useCases.toggleSettingsUseCase
import com.rafael.tictactoe.settings.settingsScreen.domain.util.rememberComponentValidator
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.SHAPE_CUSTOM
import kotlin.math.roundToInt
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SettingsBlockWithIcon(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    groupType: SettingsGroup,
    @StringRes groupName: Int,
    settings: ImmutableList<SettingItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val isAboutShown = state.isAboutShown
    val winnerLineColor by animateColorAsState(state.winnerLineColor)
    val circleColor by animateColorAsState(state.circleColor)
    val xColor by animateColorAsState(state.xColor)
    val componentValidator = rememberComponentValidator()
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    val rgbGradientBrush = Brush.linearGradient(
        colors = listOf(Color.Red, Color.Yellow),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
            .animateContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(groupName),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(bottom = 6.dp)
                .fillMaxWidth(if (isInLandscape) 0.7f else 0.95f),
        )
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp),
            ),
            modifier = Modifier
                .fillMaxWidth(if (isInLandscape) 0.65f else 0.93f)
                .clip(RoundedCornerShape(26.dp)),
        ) {
            settings.forEachIndexed { index, setting ->
                val rowHeight by animateDpAsState(
                    if (groupType == SettingsGroup.Others && index == 0 && isAboutShown) 130.dp else 58.dp,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow,
                    ),
                )
                Box(
                    modifier = Modifier
                        .height(rowHeight)
                        .fillMaxWidth()
                        .clickable {
                            toggleSettingsUseCase(
                                groupType,
                                index,
                                state,
                                onEvent,
                                launcher,
                                navController,
                            )
                        },
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .animateContentSize()
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                    ) {
                        if (setting.leadingIcon != null) {
                            Icon(
                                imageVector = setting.leadingIcon,
                                contentDescription = "Icon",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .size(32.dp),
                            )
                        }
                        if (componentValidator.validateCanvas(groupType)) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color.Black.copy(alpha = 0.15f))
                                    .size(35.dp)
                                    .align(Alignment.CenterVertically),
                                contentAlignment = Alignment.Center,
                            ) {
                                Box(
                                    modifier = Modifier.size(28.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Canvas(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                    ) {
                                        when (index) {
                                            0 -> drawLine(
                                                strokeWidth = 5.dp.toPx(),
                                                start = Offset(x = 26.dp.toPx(), y = 2.dp.toPx()),
                                                end = Offset(x = 2.dp.toPx(), y = 26.dp.toPx()),
                                                color = winnerLineColor,
                                            )
                                            1 -> drawCircle(
                                                center = Offset(14.dp.toPx(), 14.dp.toPx()),
                                                radius = 12.dp.toPx(),
                                                style = Stroke(width = 5.dp.toPx()),
                                                color = circleColor,
                                            )
                                            2 -> {
                                                drawLine(
                                                    strokeWidth = 5.dp.toPx(),
                                                    start = Offset(x = 2.dp.toPx(), y = 2.dp.toPx()),
                                                    end = Offset(x = 26.dp.toPx(), y = 26.dp.toPx()),
                                                    color = xColor,
                                                )
                                                drawLine(
                                                    strokeWidth = 5.dp.toPx(),
                                                    start = Offset(x = 26.dp.toPx(), y = 2.dp.toPx()),
                                                    end = Offset(x = 2.dp.toPx(), y = 26.dp.toPx()),
                                                    color = xColor,
                                                )
                                            }
                                        }
                                    }
                                    if (index == 3) {
                                        Card(
                                            shape = RoundedCornerShape((state.boardCardsCornerRadius / 4).dp),
                                            border = if (state.boardCardsThickness != 0f && state.shapeSelected == SHAPE_CUSTOM) {
                                                if (state.isRGBEffectOn) {
                                                    BorderStroke((state.boardCardsThickness / 2).dp, rgbGradientBrush)
                                                } else {
                                                    BorderStroke((state.boardCardsThickness / 2).dp, state.boardCardsBorderColor)
                                                }
                                            } else {
                                                null
                                            },
                                            colors = CardDefaults.cardColors(
                                                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(state.boardCardsElevation.dp),
                                            ),
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(2.dp)
                                                .then(
                                                    if (state.boardCardsShadow != 0f && index == 3) {
                                                        Modifier.shadow(state.boardCardsShadow.roundToInt().dp, RoundedCornerShape((state.boardCardsCornerRadius / 4).dp))
                                                    } else {
                                                        Modifier
                                                    },
                                                ),
                                        ) { /* No content here */ }
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .width(
                                    if (isInLandscape) {
                                        350.dp
                                    } else if (componentValidator.validateAboutAppSubComp(groupType, index, isAboutShown)) {
                                        250.dp
                                    } else {
                                        270.dp
                                    },
                                )
                                .padding(start = 16.dp, end = 10.dp)
                                .animateContentSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Text(
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                text = setting.searchSpecs.text,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start,
                            )
                            if (componentValidator.validateAboutAppSubComp(groupType, index, isAboutShown)) {
                                Text(
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    text = stringResource(R.string.creator_message) + "\n" +
                                        stringResource(R.string.app_version) + " " +
                                        getVersionName() + " (" + getVersionCode() + ")",
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                )
                            }
                        }
                        if (componentValidator.validateAboutAppSubComp(groupType, index, isAboutShown)) {
                            SubcomposeAsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data("https://avatars.githubusercontent.com/u/93414086?s=96&v=4")
                                    .crossfade(true)
                                    .build(),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(45.dp)
                                    .clip(CircleShape),
                            ) {
                                if (painter.state is AsyncImagePainter.State.Success) {
                                    SubcomposeAsyncImageContent()
                                } else {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .size(30.dp),
                                        strokeCap = StrokeCap.Round,
                                        strokeWidth = 5.dp,
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.CenterEnd,
                        ) {
                            Icon(
                                imageVector = setting.trailingIcon!!,
                                contentDescription = "Icon",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .size(32.dp),
                            )
                        }
                    }
                }
                if (index < (settings.size - 1)) {
                    HorizontalDivider(
                        thickness = 4.dp,
                        color = MaterialTheme.colorScheme.surface,
                    )
                }
            }
        }
    }
}

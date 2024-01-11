package com.rafael.tictactoe.settings.settingsScreen.presentation.components

import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.getVersionCode
import com.rafael.tictactoe.common.domain.util.getVersionName
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.SettingsGroup
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItem
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsScreen.domain.useCases.toggleSettingsUseCase
import com.rafael.tictactoe.settings.settingsScreen.domain.util.ComponentValidator
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
    val componentValidator = ComponentValidator()
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

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
                .fillMaxWidth(if (isInLandscape) 0.65f else 0.92f)
                .clip(RoundedCornerShape(26.dp)),
        ) {
            settings.forEachIndexed { index, setting ->
                val rowHeight by animateDpAsState(
                    if (groupType == SettingsGroup.Others && index == 0 && isAboutShown) 130.dp else 58.dp,
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
                                    .background(Color.Black.copy(alpha = 0.12f))
                                    .size(32.dp)
                                    .align(Alignment.CenterVertically),
                                contentAlignment = Alignment.Center,
                            ) {
                                Box(
                                    modifier = Modifier.size(24.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Canvas(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                    ) {
                                        when (index) {
                                            0 -> drawLine(
                                                strokeWidth = 5.dp.toPx(),
                                                start = Offset(x = 24.dp.toPx(), y = 0.dp.toPx()),
                                                end = Offset(x = 0.dp.toPx(), y = 24.dp.toPx()),
                                                color = winnerLineColor,
                                            )
                                            1 -> drawCircle(
                                                center = Offset(12.dp.toPx(), 12.dp.toPx()),
                                                radius = 12.dp.toPx(),
                                                style = Stroke(width = 5.dp.toPx()),
                                                color = circleColor,
                                            )
                                            2 -> {
                                                drawLine(
                                                    strokeWidth = 5.dp.toPx(),
                                                    start = Offset(x = 0.dp.toPx(), y = 0.dp.toPx()),
                                                    end = Offset(x = 24.dp.toPx(), y = 24.dp.toPx()),
                                                    color = xColor,
                                                )
                                                drawLine(
                                                    strokeWidth = 5.dp.toPx(),
                                                    start = Offset(x = 24.dp.toPx(), y = 0.dp.toPx()),
                                                    end = Offset(x = 0.dp.toPx(), y = 24.dp.toPx()),
                                                    color = xColor,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .width(if (isInLandscape) 350.dp else 240.dp)
                                .padding(start = 16.dp, end = 10.dp)
                                .animateContentSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Text(
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                text = stringResource(setting.text),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start,
                            )
                            if (componentValidator.validateSubText(groupType, index, isAboutShown)) {
                                Text(
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    text = stringResource(R.string.app_version) + " " + getVersionName() + "(" + getVersionCode() + ")",
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                )
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

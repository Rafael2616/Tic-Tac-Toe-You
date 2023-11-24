package com.rafael.tictactoe.settings.presentation.settings_screen.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.getVersionCode
import com.rafael.tictactoe.common.domain.util.getVersionName
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.domain.model.SettingsItem
import com.rafael.tictactoe.settings.domain.model.SettingsState
import com.rafael.tictactoe.settings.domain.use_cases.toggleSettingsUseCase
import com.rafael.tictactoe.settings.domain.util.ComponentValidator

@Composable
fun SettingsWithIcon(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    groupType: GroupType,
    @StringRes groupName: Int,
    settings: List<SettingsItem>
) {
    val isAboutShown = state.isAboutShown
    val winnerLineColor by animateColorAsState(state.winnerLineColor)
    val circleColor by animateColorAsState(state.circleColor)
    val xColor by animateColorAsState(state.xColor)
    val componentValidator = ComponentValidator()
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
            .animateContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(groupName),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding( bottom = 6.dp)
                .fillMaxWidth(if (isInLandscape) 0.7f else 0.95f)
        )
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
            ),
            modifier = Modifier
                .fillMaxWidth(if (isInLandscape) 0.6f else 0.9f)
                .clip(RoundedCornerShape(26.dp))
        ) {
            settings.forEachIndexed { index, setting ->
                val rowHeight by animateDpAsState(
                    if (groupType == GroupType.OTHER && index == 0 && isAboutShown) 150.dp else 60.dp
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(rowHeight)
                        .animateContentSize()
                        .fillMaxSize()
                        .clickable {
                            toggleSettingsUseCase(
                                groupType,
                                index,
                                state,
                                onEvent
                            )
                        },
                ) {
                    if (setting.leadingIcon != null) {
                        Icon(
                            imageVector = setting.leadingIcon,
                            contentDescription = "Icon",
       		        	 modifier = Modifier
                                .size(42.dp)
                                .padding(start = 10.dp)
                        )
                    }
                    if (componentValidator.validateCanvas(groupType)) {
                        Box(
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.12f))
                                .size(40.dp)
                                .align(Alignment.CenterVertically),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier.size(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Canvas(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    when (index) {
                                        0 -> drawLine(
                                            strokeWidth = 5.dp.toPx(),
                                            start = Offset(x = 24.dp.toPx(), y = 0.dp.toPx()),
                                            end = Offset(x = 0.dp.toPx(), y = 24.dp.toPx()),
                                            color = winnerLineColor
                                        )
                                        1 -> drawCircle(
                                            center = Offset(12.dp.toPx(), 12.dp.toPx()),
                                            radius = 12.dp.toPx(),
                                            style = Stroke(width = 5.dp.toPx()),
                                            color = circleColor
                                        )
                                        2 -> {
                                            drawLine(
                                                strokeWidth = 5.dp.toPx(),
                                                start = Offset(x = 0.dp.toPx(), y = 0.dp.toPx()),
                                                end = Offset(x = 24.dp.toPx(), y = 24.dp.toPx()),
                                                color = xColor
                                            )
                                            drawLine(
                                                strokeWidth = 5.dp.toPx(),
                                                start = Offset(x = 24.dp.toPx(), y = 0.dp.toPx()),
                                                end = Offset(x = 0.dp.toPx(), y = 24.dp.toPx()),
                                                color = xColor
                                            )
                                        }
                                    }
                                }   
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .animateContentSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            text = stringResource(setting.text),
                            modifier = Modifier.width(220.dp),
                            textAlign = TextAlign.Center
                        ) 
                        if (componentValidator.validateSubText(groupType, index, isAboutShown)) {
                            Text(
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                text = stringResource(R.string.app_version) +" "+ getVersionName() + "(" + getVersionCode() + ")",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .width(220.dp)
                                    .padding(top = 8.dp)
                                
                            )
                        }
                    }
                    Icon(
                        imageVector = setting.trailingIcon!!,
                        contentDescription = "Icon",
       		    	 modifier = Modifier
                            .size(42.dp)
                            .padding(end = 10.dp)
                    )
                }
                if (index < (settings.size - 1)) {
                    HorizontalDivider(
                        thickness = 4.dp,
                        color = MaterialTheme.colorScheme.surface
                    )
                }
            }
        }
    }
}
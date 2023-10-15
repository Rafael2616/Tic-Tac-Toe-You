package com.rafael.tictactoe.settings.presentation.settings_screen.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.getVersionCode
import com.rafael.tictactoe.common.domain.util.getVersionName
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.domain.use_cases.toggleSettingsUseCase
import com.rafael.tictactoe.settings.domain.util.ComponentValidator
import com.rafael.tictactoe.settings.domain.util.SettingsFirstIconHandler
import com.rafael.tictactoe.settings.domain.util.SettingsSecondIconHandler
import com.rafael.tictactoe.settings.domain.util.SettingsTextHandler
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SettingsWithIcon(
    size: List<Int>,
    groupType: GroupType,
    @StringRes groupName: Int
) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val isAboutShown by viewModel.isAboutShown.collectAsState()
    val winnerLineColor by viewModel.winnerLineColor.collectAsState()
    val circleColor by viewModel.circleColor.collectAsState()
    val xColor by viewModel.xColor.collectAsState()
    val componentValidator = ComponentValidator()

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
                .padding(start = 14.dp, bottom = 6.dp)
                .fillMaxWidth()
        )
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .animateContentSize()
                .width(360.dp)
                .clip(RoundedCornerShape(26.dp))
        ) {
            size.forEachIndexed { _, index ->
                val rowHeight by animateDpAsState(
                    if (groupType == GroupType.OTHER && index == 0 && isAboutShown) 150.dp else 62.dp
                )
                Row(
                    modifier = Modifier
                        .height(rowHeight)
                        .fillMaxSize()
                        .clickable(onClick = {
                            viewModel.execUIClickSound()
                            toggleSettingsUseCase(
                                groupType,
                                index,
                                viewModel
                            )
                        }),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (componentValidator.validateFirstIcon(groupType)) {
                        Icon(
                            imageVector = SettingsFirstIconHandler(index, groupType),
                            contentDescription = "Icon",
       		        	 modifier = Modifier
                                .size(45.dp)
                                .padding(start = 8.dp)
                        )
                    }
                    if (componentValidator.validateCanvas(groupType)) {
                        Canvas(
                            modifier = Modifier
                                .size(50.dp)
                        ) {
                            when (index) {
                                0 -> drawLine(
                                    strokeWidth = 5.dp.toPx(),
                                    start = Offset(x = 40.dp.toPx(), y = 15.dp.toPx()),
                                    end = Offset(x = 15.dp.toPx(), y = 40.dp.toPx()),
                                    color = winnerLineColor
                                )
                                1 -> drawCircle(
                                    center = Offset(28.dp.toPx(), 28.dp.toPx()),
                                    radius = 15.dp.toPx(),
                                    style = Stroke(width = 5.dp.toPx()),
                                    color = circleColor
                                )
                                2 -> {
                                    drawLine(
                                        strokeWidth = 5.dp.toPx(),
                                        start = Offset(x = 15.dp.toPx(), y = 15.dp.toPx()),
                                        end = Offset(x = 40.dp.toPx(), y = 40.dp.toPx()),
                                        color = xColor
                                    )
                                    drawLine(
                                        strokeWidth = 5.dp.toPx(),
                                        start = Offset(x = 40.dp.toPx(), y = 15.dp.toPx()),
                                        end = Offset(x = 15.dp.toPx(), y = 40.dp.toPx()),
                                        color = xColor
                                    )
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
                            text = SettingsTextHandler(index, groupType),
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
                        imageVector = SettingsSecondIconHandler(index, groupType),
                        contentDescription = "Icon",
       		    	 modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                    )
                }
                if (index < (size.size - 1)) {
                    HorizontalDivider(thickness = 4.dp, color = MaterialTheme.colorScheme.surface)
                }
            }
        }
    }
}
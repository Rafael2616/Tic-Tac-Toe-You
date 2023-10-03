package com.rafael.tictactoe.settings.presentation.settings_screen.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.getAppVersion
import com.rafael.tictactoe.settings.domain.util.ComponentValidator
import com.rafael.tictactoe.settings.domain.util.GroupType
import com.rafael.tictactoe.settings.domain.util.SettingsIconHandler
import com.rafael.tictactoe.settings.domain.util.SettingsImageHandler
import com.rafael.tictactoe.settings.domain.util.SettingsTextHandler
import com.rafael.tictactoe.settings.domain.use_cases.toggleSettingsUseCase
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SettingsWithIcon(
    size: List<Int>,
    groupType: GroupType,
    @StringRes groupName: Int
) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val isAboutShown by viewModel.isAboutShown.collectAsState()
    val winnerLineColor by viewModel.winnerLineColor.collectAsState()
    val componentValidator = ComponentValidator()

    Column(
        modifier = Modifier
            .fillMaxSize()
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
            size.forEachIndexed { _, index ->
                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .animateContentSize()
                        .width(360.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .height(if (groupType == GroupType.OTHER && index == 0 && isAboutShown) 150.dp else 68.dp)
                        .clickable(onClick = {
                            viewModel.execUIClickSound(context)
                            toggleSettingsUseCase(
                                groupType,
                                context,
                                index,
                                viewModel
                            )
                        })
                    ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (componentValidator.validateImage(groupType, index)) {
                        Image(
                            contentDescription = "Setting Image",
                            modifier = Modifier
                                .size(55.dp),
                            painter = SettingsImageHandler(index, groupType)
                        )
                    }
                    if (componentValidator.validateBoxWithLinePreview(groupType, index)) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .width(15.dp)
                                .padding(4.dp)
                                .rotate(45f)
                                .clip(RectangleShape)
                                .background(winnerLineColor)
                        )
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
                            modifier = Modifier.width(240.dp),
                            textAlign = TextAlign.Center
                        ) 
                        if (componentValidator.validateSubText(groupType, index, isAboutShown)) {
                            Text(
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                text = stringResource(R.string.app_version) +" "+ getAppVersion(context),
                                modifier = Modifier.width(240.dp).padding(top = 8.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }           
                    Icon(
                        imageVector = SettingsIconHandler(index, groupType),
                        contentDescription = "Icon",
       		    	 modifier = Modifier.size(35.dp)
                    )         
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
    
    DisposableEffect(Unit) {
        onDispose {
            viewModel.updateIsAboutShown(false)
        }
    }
}
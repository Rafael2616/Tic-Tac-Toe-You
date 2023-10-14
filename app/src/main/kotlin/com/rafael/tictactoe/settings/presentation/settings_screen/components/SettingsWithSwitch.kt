package com.rafael.tictactoe.settings.presentation.settings_screen.components

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.domain.use_cases.toggleSettingsUseCase
import com.rafael.tictactoe.settings.domain.util.ComponentValidator
import com.rafael.tictactoe.settings.domain.util.SettingsFirstIconHandler
import com.rafael.tictactoe.settings.domain.util.SettingsImageHandler
import com.rafael.tictactoe.settings.domain.util.SettingsTextHandler
import com.rafael.tictactoe.settings.domain.util.switchCheckedHandler
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SettingsWithSwitch(
    size: List<Int>,
    groupType: GroupType,
    @StringRes groupName: Int
) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val isFollowingSystemOn by viewModel.isFollowingSystemOn.collectAsState()
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
                if (componentValidator.validateCard(
                    groupType,
                    index,
                    isFollowingSystemOn
                )) {
                    Row(
                        modifier = Modifier
                            .height(62.dp)
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
                        if (componentValidator.validateImage(groupType)) {
                            Image(
                                contentDescription = "Setting Image",
                                modifier = Modifier
                                    .size(55.dp),
                                painter = SettingsImageHandler(index, groupType)
                            )
                        }
                        if (componentValidator.validateFirstIcon(groupType)) {
                           Icon(
                                imageVector = SettingsFirstIconHandler(index, groupType),
                                contentDescription = "Icon",
                                tint = if (switchCheckedHandler(
                                    groupType,
                                    index,
                                    viewModel
                                )) {
                                     MaterialTheme.colorScheme.onSurface
                                } else {
                                     MaterialTheme.colorScheme.error
                                },
       		            	 modifier = Modifier
                                    .size(45.dp)
                                   .padding(start = 8.dp)
                            )
                        }
                        Text(
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            text = SettingsTextHandler(index, groupType),
                            modifier = Modifier.width(220.dp),
                            textAlign = TextAlign.Center
                        )
                        Switch(
                            modifier = Modifier.padding(end = 8.dp),
                            checked = switchCheckedHandler(
                                groupType,
                                index,
                                viewModel
                            ),
                            onCheckedChange = {
                                toggleSettingsUseCase(
                                    groupType,
                                    index,
                                    viewModel
                                )
                                viewModel.execUIClickSound()
                            },
                            thumbContent = {
                                 if (switchCheckedHandler(
                                        groupType,
                                        index,
                                        viewModel
                                    )) {
                                    Icon(
     					               imageVector = Icons.Filled.Check,
    					                contentDescription = "Setting Switcher",
       					             modifier = Modifier.size(SwitchDefaults.IconSize)
                                    )
                                } else {
                                    Unit
                                }
   					     }
                        )
                    }
                    if ((index < (size.size - 1)) && componentValidator.validateDivider(
                        groupType,
                        index,
                        isFollowingSystemOn
                    )) {
                        HorizontalDivider(
                            thickness = 4.dp,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                }
            }
        }
    }
}
package com.rafael.tictactoe.settings.presentation.settings_screen.components

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.domain.util.GroupType
import com.rafael.tictactoe.settings.domain.util.SettingsImageHandler
import com.rafael.tictactoe.settings.domain.util.SettingsTextHandler
import com.rafael.tictactoe.settings.domain.util.switchCheckedHandler
import com.rafael.tictactoe.settings.domain.use_cases.toggleSettingsUseCase
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SettingsWithSwitch(
    size: List<Int>,
    groupType: GroupType,
    @StringRes groupName: Int
) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val isFollowingSystemOn by viewModel.isFollowingSystemOn.collectAsState()
    val isDynamicColorsSupported = VERSION.SDK_INT >= VERSION_CODES.S

    fun validate(groupType: GroupType, index: Int): Boolean {
        if (groupType == GroupType.THEME && ((index == 1 && !isFollowingSystemOn) ||
        (index == 2 && isDynamicColorsSupported) ||
        (index != 1 && index != 2))) {
            return true
        }
        if (groupType != GroupType.THEME) {
            return true
        }
        if (groupType == GroupType.THEME && ((index == 1 && isFollowingSystemOn) ||
        (index == 2 && !isDynamicColorsSupported))) {
            return false
        }

        return false
    }
    
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
            if (validate(groupType, index)) {
                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .animateContentSize()
                        .width(360.dp)
                        .height(68.dp)
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
                        Image(
                            contentDescription = "Setting Image",
                            modifier = Modifier
                                .height(55.dp)
                                .width(55.dp),
                            painter = SettingsImageHandler(index, groupType)
                        )
                        Text(
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            text = SettingsTextHandler(index, groupType),
                            modifier = Modifier.width(240.dp),
                            textAlign = TextAlign.Center
                        )
                        Switch(
                            checked = switchCheckedHandler(
                                groupType,
                                index,
                                viewModel
                            ),
                            onCheckedChange = {
                                toggleSettingsUseCase(
                                    groupType,
                                    context,
                                    index,
                                    viewModel
                                )
                                viewModel.execUIClickSound(context)
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
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}
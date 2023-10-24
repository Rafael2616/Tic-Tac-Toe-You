package com.rafael.tictactoe.settings.presentation.settings_screen.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.domain.model.SettingsState
import com.rafael.tictactoe.settings.domain.use_cases.toggleSettingsUseCase
import com.rafael.tictactoe.settings.domain.util.ComponentValidator
import com.rafael.tictactoe.settings.domain.util.SettingsFirstIconHandler
import com.rafael.tictactoe.settings.domain.util.SettingsTextHandler
import com.rafael.tictactoe.settings.domain.util.switchCheckedHandler

@Composable
fun SettingsWithSwitch(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    size: List<Int>,
    groupType: GroupType,
    @StringRes groupName: Int
) {
    val isFollowingSystemOn = state.isFollowingSystemOn
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
                .padding(bottom = 6.dp)
                .fillMaxWidth(if (isInLandscape) 0.7f else 0.95f)
        )
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
            ),
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth(if (isInLandscape) 0.6f else 0.9f)
                .clip(RoundedCornerShape(26.dp))
        ) {
            size.forEachIndexed { _, index ->
                if (componentValidator.validateCard(
                    groupType,
                    index,
                    isFollowingSystemOn
                )) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxSize()
                            .clickable(onClick = {
                                toggleSettingsUseCase(
                                    groupType,
                                    index,
                                    state,
                                    onEvent
                                )
                            })
                    ) {
                        if (componentValidator.validateFirstIcon(groupType)) {
                           Icon(
                                imageVector = SettingsFirstIconHandler(index, groupType, state),
                                contentDescription = "Icon",
       		            	 modifier = Modifier
                                    .size(42.dp)
                                   .padding(start = 10.dp)
                            )
                        }
                        Text(
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            text = SettingsTextHandler(index, groupType, state),
                            modifier = Modifier.width(220.dp),
                            textAlign = TextAlign.Center
                        )
                        Switch(
                            modifier = Modifier.padding(end = 10.dp),
                            checked = switchCheckedHandler(
                                groupType,
                                index,
                                state
                            ),
                            onCheckedChange = {
                                toggleSettingsUseCase(
                                    groupType,
                                    index,
                                    state,
                                    onEvent
                                )
                            },
                            thumbContent = {
                                 if (switchCheckedHandler(
                                        groupType,
                                        index,
                                        state
                                    )) {
                                    Icon(
     					               imageVector = Icons.Filled.Check,
    					                contentDescription = "Setting Switcher",
       					             modifier = Modifier.size(SwitchDefaults.IconSize)
                                    )
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
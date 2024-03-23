package com.rafael.tictactoe.settings.settingsScreen.presentation.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.SettingsGroup
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItem
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsScreen.domain.useCases.toggleSettingsUseCase
import com.rafael.tictactoe.settings.settingsScreen.domain.util.rememberComponentValidator
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SettingsBlockWithSwitch(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    groupType: SettingsGroup,
    @StringRes groupName: Int,
    settings: ImmutableList<SettingItem>,
    modifier: Modifier = Modifier,
) {
    val isFollowingSystemOn = state.isFollowingSystemOn
    val componentValidator = rememberComponentValidator()
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = orientation == Configuration.ORIENTATION_LANDSCAPE

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
                .animateContentSize()
                .fillMaxWidth(if (isInLandscape) 0.65f else 0.93f)
                .clip(RoundedCornerShape(26.dp)),
        ) {
            settings.forEachIndexed { index, setting ->
                if (componentValidator.validateCard(
                        groupType,
                        index,
                        isFollowingSystemOn,
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .height(58.dp)
                            .fillMaxWidth()
                            .clickable {
                                toggleSettingsUseCase(
                                    groupType,
                                    index,
                                    state,
                                    onEvent,
                                )
                            },
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp),
                        ) {
                            Icon(
                                imageVector = setting.leadingIcon!!,
                                contentDescription = "Icon",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .size(32.dp),
                            )
                            Text(
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                text = setting.searchSpecs.text,
                                modifier = Modifier
                                    .width(220.dp)
                                    .padding(start = 16.dp, end = 10.dp),
                                textAlign = TextAlign.Start,
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.CenterEnd,
                            ) {
                                Switch(
                                    checked = setting.isChecked!!,
                                    onCheckedChange = {
                                        toggleSettingsUseCase(
                                            groupType,
                                            index,
                                            state,
                                            onEvent,
                                        )
                                    },
                                    thumbContent = {
                                        if (setting.isChecked) {
                                            Icon(
                                                imageVector = Icons.Filled.Check,
                                                contentDescription = "Setting Switcher",
                                                modifier = Modifier.size(SwitchDefaults.IconSize),
                                            )
                                        }
                                    },
                                )
                            }
                        }
                    }
                    if ((index < (settings.size - 1)) && componentValidator.validateDivider(
                            groupType,
                            index,
                            isFollowingSystemOn,
                        )
                    ) {
                        HorizontalDivider(
                            thickness = 4.dp,
                            color = MaterialTheme.colorScheme.surface,
                        )
                    }
                }
            }
        }
    }
}

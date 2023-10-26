package com.rafael.tictactoe.settings.presentation.settings_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.domain.model.SettingsState
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsWithIcon
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsWithSwitch

@Composable
fun SettingsOptionsImpl(
    livePadding: PaddingValues,
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState
) {
    val settingsGroups by remember { mutableIntStateOf(4) }
    val themeGroupSize by remember { mutableIntStateOf(3) }
    val soundGroupSize by remember { mutableIntStateOf(2) }
    val customizationGroupSize by remember { mutableIntStateOf(3) }
    val otherGroupSize by remember { mutableIntStateOf(1) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(livePadding)
            .animateContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(settingsGroups) { group ->
            when (group) {
                0 -> SettingsWithSwitch(
                    state = state,
                    onEvent = onEvent,
                    groupSize = themeGroupSize,
                    groupType = GroupType.THEME,
                    groupName = R.string.group_theme
				)
                1 -> SettingsWithSwitch(
                    state = state,
                    onEvent = onEvent,
                    groupSize = soundGroupSize,
                    groupType = GroupType.SOUND,
                    groupName = R.string.group_sound
                )
                2 -> SettingsWithIcon(
                    state = state,
                    onEvent = onEvent,
                    groupSize = customizationGroupSize,
                    groupType = GroupType.CUSTOMIZATION,
                    groupName = R.string.group_customization
                )
                3 -> SettingsWithIcon(
                    state = state,
                    onEvent = onEvent,
                    groupSize = otherGroupSize,
                    groupType = GroupType.OTHER,
                    groupName = R.string.group_others
                )
            }
        }
    }
}
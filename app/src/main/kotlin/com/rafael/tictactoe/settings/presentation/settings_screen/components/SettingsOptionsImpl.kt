package com.rafael.tictactoe.settings.presentation.settings_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.domain.enums.Customization
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.domain.enums.Other
import com.rafael.tictactoe.settings.domain.enums.Sound
import com.rafael.tictactoe.settings.domain.enums.Theme
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
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(livePadding)
            .animateContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(GroupType.values()) { group ->
            when (group) {
                GroupType.THEME -> SettingsWithSwitch(
                    state = state,
                    onEvent = onEvent,
                    groupSize = Theme.values().size,
                    groupType = GroupType.THEME,
                    groupName = R.string.group_theme
				)
                GroupType.SOUND -> SettingsWithSwitch(
                    state = state,
                    onEvent = onEvent,
                    groupSize = Sound.values().size,
                    groupType = GroupType.SOUND,
                    groupName = R.string.group_sound
                )
                GroupType.CUSTOMIZATION -> SettingsWithIcon(
                    state = state,
                    onEvent = onEvent,
                    groupSize = Customization.values().size,
                    groupType = GroupType.CUSTOMIZATION,
                    groupName = R.string.group_customization
                )
                GroupType.OTHER -> SettingsWithIcon(
                    state = state,
                    onEvent = onEvent,
                    groupSize = Other.values().size,
                    groupType = GroupType.OTHER,
                    groupName = R.string.group_others
                )
            }
        }
    }
}
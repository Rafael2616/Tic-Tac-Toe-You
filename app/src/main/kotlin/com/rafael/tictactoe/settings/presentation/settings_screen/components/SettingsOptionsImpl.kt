package com.rafael.tictactoe.settings.presentation.settings_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsWithIcon
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsWithSwitch
import com.rafael.tictactoe.settings.domain.util.GroupType

@Composable
fun SettingsOptionsImpl(paddingValues: PaddingValues) {
    var settingsGroups by rememberSaveable { mutableStateOf(List<Boolean>(4) { false }) }
    val themeGroupSize = listOf(0,1,2)
    val soundGroupSize = listOf(0,1)
    val cutomizationGroupSize = listOf(0)
    val otherGroupSize = listOf(0)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .animateContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {   
        items(settingsGroups.size) { index ->
            when (index) {
              0 -> SettingsWithSwitch(
                    size = themeGroupSize,
                    groupType = GroupType.THEME,
                    groupName = R.string.group_theme
				)
                1 -> SettingsWithSwitch(
                    size = soundGroupSize,
                    groupType = GroupType.SOUND,
                    groupName = R.string.group_sound
                )
                2 -> SettingsWithIcon(
                    size = cutomizationGroupSize,
                    groupType = GroupType.CUSTOMIZATION,
                    groupName = R.string.group_customization
                )
                3 -> SettingsWithIcon(
                    size = otherGroupSize,
                    groupType = GroupType.OTHER,
                    groupName = R.string.group_others
                )
            }
        }
    }
}
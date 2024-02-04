package com.rafael.tictactoe.settings.settingsScreen.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.SettingsGroup
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsScreen.presentation.utils.getSettingsItems

@Composable
fun SettingsOptionsImpl(
    livePadding: PaddingValues,
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val settings = getSettingsItems(state, context)
    val lazyState = rememberLazyListState()
    
    LazyColumn(
        state = lazyState,
        modifier = modifier
            .fillMaxSize()
            .padding(livePadding)
            .animateContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(SettingsGroup.values()) { group ->
            when (group) {
                SettingsGroup.Theme -> SettingsBlockWithSwitch(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Theme,
                    groupName = R.string.group_theme,
                    settings = settings[0],
                )
                SettingsGroup.Sound -> SettingsBlockWithSwitch(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Sound,
                    groupName = R.string.group_sound,
                    settings = settings[1],
                )
                SettingsGroup.Customization -> SettingsBlockWithIcon(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Customization,
                    groupName = R.string.group_customization,
                    settings = settings[2],
                    navController = navController,
                )
                SettingsGroup.Others -> SettingsBlockWithIcon(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Others,
                    groupName = R.string.group_others,
                    settings = settings[3],
                    navController = navController,
                )
            }
        }
    }
}

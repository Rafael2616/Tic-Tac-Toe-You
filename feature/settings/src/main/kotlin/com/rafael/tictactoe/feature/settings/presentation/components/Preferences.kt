package com.rafael.tictactoe.settings.settingsScreen.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.groups.CustomizationPreferenceGroup
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.groups.OthersPreferenceGroup
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.groups.SoundPreferenceGroup
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.groups.ThemePreferenceGroup

@Composable
fun Preferences(
    innerPadding: PaddingValues,
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val lazyState = rememberLazyListState()

    LazyColumn(
        state = lazyState,
        modifier = modifier
            .fillMaxSize()
            .consumeWindowInsets(innerPadding)
            .animateContentSize(),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            ThemePreferenceGroup(onEvent, state)
        }
        item {
            SoundPreferenceGroup(onEvent, state)
        }
        item {
            CustomizationPreferenceGroup(onEvent, state)
        }
        item {
            OthersPreferenceGroup(onEvent, state, navController)
        }
    }
}

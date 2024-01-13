package com.rafael.tictactoe.settings.settingsScreen.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.game.presentation.mainScreen.components.WhatsNewDialog
import com.rafael.tictactoe.settings.searchSettingsScreen.presentation.viewmodels.SearchSettingsViewModel
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.presentation.components.ColorSelectionDialog
import com.rafael.tictactoe.settings.settingsScreen.presentation.components.SettingsTopBar
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun SettingsScreen(
    navController: NavController,
    settingsVM: SettingsViewModel = hiltViewModel(),
    searchSettingsVM: SearchSettingsViewModel = hiltViewModel(),
) {
    val state by settingsVM.state.collectAsState()

    SettingsTopBar(
        navController,
        settingsVM::onEvent,
        state,
        searchSettingsVM::onEvent,
    )

    AnimatedVisibility(state.showColorSelectionDialog) {
        ColorSelectionDialog(settingsVM::onEvent, state)
    }

    AnimatedVisibility(state.showWhatsNewDialog) {
        WhatsNewDialog(
            settingsVM::onEvent,
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            settingsVM.onEvent(SettingsEvent.OnSettingsScreenDestroyment)
        }
    }

    BackHandler {
        if (state.showColorSelectionDialog) {
            settingsVM.onEvent(SettingsEvent.ShowColorSelectionDialog(false))
        }
        if (state.showWhatsNewDialog) {
            settingsVM.onEvent(SettingsEvent.HideWhatsNewDialog)
        }
        if (!state.showWhatsNewDialog && !state.showColorSelectionDialog) {
            navController.popBackStack()
        }
    }
}

package com.rafael.tictactoe.settings.presentation.settingsScreen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.presentation.settingsScreen.components.ColorSelectionDialog
import com.rafael.tictactoe.settings.presentation.settingsScreen.components.SettingsTopBar
import com.rafael.tictactoe.settings.presentation.settingsScreen.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun SettingsScreen(navController: NavController) {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val state by settingsVM.state.collectAsState()

    SettingsTopBar(
        navController,
        settingsVM::onEvent,
        state,
    )

    AnimatedVisibility(state.showColorSelectionDialog) {
        ColorSelectionDialog(settingsVM::onEvent, state)
    }

    DisposableEffect(Unit) {
        onDispose {
            settingsVM.onEvent(SettingsEvent.OnSettingsScreenDestroyment)
        }
    }

    BackHandler {
        if (state.showColorSelectionDialog) {
            settingsVM.onEvent(SettingsEvent.ShowColorSelectionDialog(false))
        } else {
            navController.popBackStack()
        }
    }
}

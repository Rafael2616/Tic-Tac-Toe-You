package com.rafael.tictactoe.settings.settingsScreen.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.game.mainScreen.presentation.components.WhatsNewDialog
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.presentation.components.SettingsOptionsImpl
import com.rafael.tictactoe.settings.settingsScreen.presentation.components.SettingsTopBar
import com.rafael.tictactoe.settings.settingsScreen.presentation.components.dialogs.ColorSelectionDialog
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun SettingsScreen(
    navController: NavController,
    settingsVM: SettingsViewModel = hiltViewModel(),
) {
    val state by settingsVM.state.collectAsState()
    val onEvent = settingsVM::onEvent

    SettingsTopBar(
        navController,
        onEvent,
    ) {  innerPadding ->
        SettingsOptionsImpl(
            innerPadding,
            onEvent,
            state,
            navController,
        )
    }

    AnimatedVisibility(state.showColorSelectionDialog) {
        ColorSelectionDialog(onEvent, state)
    }

    AnimatedVisibility(state.showWhatsNewDialog) {
        WhatsNewDialog(onEvent)
    }

    DisposableEffect(Unit) {
        onDispose {
            onEvent(SettingsEvent.OnSettingsScreenDestroyment)
        }
    }
}

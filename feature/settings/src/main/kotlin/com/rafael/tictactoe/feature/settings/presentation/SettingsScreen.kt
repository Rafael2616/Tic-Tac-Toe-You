package com.rafael.tictactoe.feature.settings.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.presentation.components.Preferences
import com.rafael.tictactoe.feature.settings.presentation.components.ProfilePictureMenu
import com.rafael.tictactoe.feature.settings.presentation.components.SettingsTopBar
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.BoardCustomizationDialog
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.ColorSelectionDialog
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun SettingsScreen(
    navController: NavController,
    settingsVM: SettingsViewModel = hiltViewModel(),
) {
    val state by settingsVM.state.collectAsStateWithLifecycle()
    val onEvent = settingsVM::onEvent

    SettingsTopBar(
        navController,
        state,
        onEvent,
    ) { innerPadding ->
        Preferences(
            innerPadding,
            state,
            onEvent,
            navController,
        )
    }

    AnimatedVisibility(state.showProfilePictureMenu) {
        ProfilePictureMenu(
            state,
            onEvent,
            settingsVM::onDeleteImage,
            settingsVM::onSaveImage,
        )
    }

    AnimatedVisibility(state.showColorSelectionDialog) {
        ColorSelectionDialog(onEvent, state)
    }

    AnimatedVisibility(state.showBoardCustomizationDialog) {
        BoardCustomizationDialog(onEvent, state)
    }

    DisposableEffect(Unit) {
        onDispose {
            onEvent(SettingsEvent.OnSettingsScreenDestroyment)
        }
    }
}

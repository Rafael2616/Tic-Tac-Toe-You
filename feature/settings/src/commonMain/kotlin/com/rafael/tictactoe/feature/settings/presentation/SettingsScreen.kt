package com.rafael.tictactoe.feature.settings.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.NavController
import coil3.Uri
import com.rafael.tictactoe.core.platform.permissions.PermissionsState
import com.rafael.tictactoe.core.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.Preferences
import com.rafael.tictactoe.feature.settings.presentation.components.ProfilePictureMenu
import com.rafael.tictactoe.feature.settings.presentation.components.SettingsTopBar
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.BoardCustomizationDialog
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.ColorSelectionDialog

@Composable
fun SettingsScreen(
    navController: NavController,
    state: SettingsState,
    permissionsState: PermissionsState,
    requestGalleryPermission: () -> Unit,
    onEvent: (SettingsEvent) -> Unit,
    onDeleteImage: () -> Unit,
    onSaveImage: (Uri) -> Unit,
) {
    val latestOnEvent by rememberUpdatedState(onEvent)

    SettingsTopBar(
        navController,
        state,
        latestOnEvent,
    ) { innerPadding ->
        Preferences(
            innerPadding,
            state,
            latestOnEvent,
            navController,
        )
    }

    AnimatedVisibility(state.showProfilePictureMenu) {
        ProfilePictureMenu(
            state,
            latestOnEvent,
            permissionsState,
            requestGalleryPermission,
            onDeleteImage,
            onSaveImage,
        )
    }

    AnimatedVisibility(state.showColorSelectionDialog) {
        ColorSelectionDialog(latestOnEvent, state)
    }

    AnimatedVisibility(state.showBoardCustomizationDialog) {
        BoardCustomizationDialog(latestOnEvent, state)
    }

    DisposableEffect(Unit) {
        onDispose {
            latestOnEvent(SettingsEvent.OnSettingsScreenDestroy)
        }
    }
}

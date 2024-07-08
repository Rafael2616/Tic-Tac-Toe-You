package com.rafael.tictactoe.feature.settings.presentation

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.Preferences
import com.rafael.tictactoe.feature.settings.presentation.components.ProfilePictureMenu
import com.rafael.tictactoe.feature.settings.presentation.components.SettingsTopBar
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.BoardCustomizationDialog
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.ColorSelectionDialog

@Composable
@ExperimentalMaterial3Api
fun SettingsScreen(
    navController: NavController,
    state: SettingsState,
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

@Preview(showBackground = true)
@PreviewLightDark
@PreviewScreenSizes
@Composable
@ExperimentalMaterial3Api
private fun SettingsScreenPreview() {
    MaterialTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            SettingsScreen(
                navController = rememberNavController(),
                state = SettingsState(),
                onEvent = {},
                onDeleteImage = {},
                onSaveImage = {},
            )
        }
    }
}

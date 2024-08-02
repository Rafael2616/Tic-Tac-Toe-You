package com.rafael.tictactoe.feature.settings.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.Uri
import com.rafael.tictactoe.core.platform.permissions.PermissionsState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState

@Composable
actual fun ProfilePictureMenu(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    permissionsState: PermissionsState,
    requestGalleryPermission: () -> Unit,
    onDeleteImage: () -> Unit,
    onSaveImage: (uri: Uri) -> Unit,
    modifier: Modifier,
) {
    TODO("Not yet implemented")
}

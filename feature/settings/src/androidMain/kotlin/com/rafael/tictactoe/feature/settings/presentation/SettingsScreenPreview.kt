package com.rafael.tictactoe.feature.settings.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.core.platform.permissions.PermissionsState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState

@Preview(showBackground = true)
@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun SettingsScreenPreview() {
    MaterialTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            SettingsScreen(
                navController = rememberNavController(),
                state = SettingsState(),
                permissionsState = PermissionsState(),
                requestGalleryPermission = {},
                onEvent = {},
                onDeleteImage = {},
                onSaveImage = {},
            )
        }
    }
}

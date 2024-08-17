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
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.core.ui.theme.AppTheme

@Preview(
    showBackground = true,
    apiLevel = 34,
)
@PreviewLightDark
@PreviewScreenSizes
@Composable
private fun SettingsScreenPreview() {
    AppTheme {
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

package com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.History
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rafael.tictactoe.core.resources.R
//import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.IconPreference

@Composable
fun UpdateHistoryPreference(
    navController: NavController,
) {
    IconPreference(
        title = R.string.update_history,
        leadingIcon = Icons.Outlined.History,
        trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
        onClick = {/* navController.navigate(Destinations.Changelogs.screen)*/ },
    )
}

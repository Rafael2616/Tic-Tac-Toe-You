package com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Code
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rafael.tictactoe.core.resources.R
//import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.IconPreference

@Composable
fun OpenSourceLicensesPreference(
    navController: NavController,
) {
    IconPreference(
        title = R.string.open_source_licenses,
        leadingIcon = Icons.Outlined.Code,
        trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
        onClick = { /* navController.navigate(Destinations.Library.screen)*/ },
    )
}

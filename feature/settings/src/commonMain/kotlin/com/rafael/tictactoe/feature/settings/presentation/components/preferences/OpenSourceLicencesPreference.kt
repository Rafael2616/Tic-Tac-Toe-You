package com.rafael.tictactoe.feature.settings.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.Code
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rafael.tictactoe.core.navigation.LibraryInfo
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.open_source_licenses
import com.rafael.tictactoe.feature.settings.presentation.components.templates.IconPreference

@Composable
fun OpenSourceLicensesPreference(
    navController: NavController,
) {
    IconPreference(
        title = Res.string.open_source_licenses,
        leadingIcon = Icons.Outlined.Code,
        trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
        onClick = { navController.navigate(LibraryInfo) },
    )
}

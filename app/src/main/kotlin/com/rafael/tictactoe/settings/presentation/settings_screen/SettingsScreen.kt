package com.rafael.tictactoe.settings.presentation.settings_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsTopBar
import com.rafael.tictactoe.settings.presentation.settings_screen.components.WinnerLineColorSelectorDialog
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun SettingsScreen(navController: NavController) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val showWinnerLineColorDialog by viewModel.showWinnerLineColorDialog.collectAsState()

    SettingsTopBar(navController)

    AnimatedVisibility(showWinnerLineColorDialog) {
        WinnerLineColorSelectorDialog()
    }
}
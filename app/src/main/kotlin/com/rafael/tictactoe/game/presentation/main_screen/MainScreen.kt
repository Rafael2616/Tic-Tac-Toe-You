package com.rafael.tictactoe.game.presentation.main_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.game.presentation.main_screen.components.MainOptions
import com.rafael.tictactoe.game.presentation.main_screen.components.MainTopBar
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun MainScreen(navController: NavController) {

    val settingsVM = hiltViewModel<SettingsViewModel>()
    val viewModel = hiltViewModel<GameViewModel>()

    MainTopBar()
    MainOptions(
        navController,
        settingsVM,
        viewModel
    )
}
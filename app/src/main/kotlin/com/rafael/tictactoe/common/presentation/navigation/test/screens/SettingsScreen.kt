package com.rafael.tictactoe.settings.presentation.settings_screen

import android.app.Activity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.TicTacToeActivity
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import com.rafael.tictactoe.settings.presentation.settings_screen.components.test.SettingsCardsImpl
import com.rafael.tictactoe.settings.presentation.settings_screen.components.test.SettingsTopBar
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun SettingsScreen(navController: NavController, viewModel: SettingsViewModel) {
    SettingsTopBar(navController, viewModel)
}
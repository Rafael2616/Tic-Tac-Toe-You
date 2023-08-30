package com.rafael.tictactoe.common.presentation.navigation

import android.app.Activity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.common.presentation.navigation.Screen
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.presentation.game_screen.GameScreen
import com.rafael.tictactoe.game.presentation.main_screen.MainScreen
import com.rafael.tictactoe.game.presentation.splash_screen.SplashScreen
import com.rafael.tictactoe.settings.presentation.settings_screen.SettingsScreen
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
@ExperimentalMaterial3Api
fun NavGraph(
    activity: Activity,
    gameUtil: GameUtils,
    settingsVM: SettingsViewModel
) {
    val navController = rememberNavController()
    //val settingsVM = viewModel<SettingsViewModel>()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController, settingsVM)
        }
        composable(Screen.Main.route) {
            MainScreen(navController, settingsVM)
        }
        composable(Screen.Game.route) {
            GameScreen(gameUtil, activity)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController, settingsVM)
        }
    }
}
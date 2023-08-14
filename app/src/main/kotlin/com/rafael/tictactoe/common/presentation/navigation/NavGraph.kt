package com.rafael.tictactoe.common.presentation.navigation

import android.app.Activity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.presentation.game_screen.GameScreen
import com.rafael.tictactoe.game.presentation.main_screen.MainScreen
import com.rafael.tictactoe.game.presentation.splash_screen.SplashScreen
import com.rafael.tictactoe.settings.presentation.settings_screen.SettingsScreen

@Composable
@ExperimentalMaterial3Api
fun NavGraph(activity: Activity, gameUtil: GameUtils) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        navigation(
            route = "gameNavigation",
            startDestination = "splash"
        ) {
            composable("splash") {
                SplashScreen(navController)
            }
            composable("main") {
                MainScreen(navController)
            }
            composable("game") {
                GameScreen(navController, gameUtil, activity)
            }
            composable("settings") {
                SettingsScreen(navController, activity)
            }
        }
    }
}

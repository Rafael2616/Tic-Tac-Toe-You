package com.rafael.tictactoe.common.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.game.presentation.game_screen.GameScreen
import com.rafael.tictactoe.game.presentation.main_screen.MainScreen
import com.rafael.tictactoe.game.presentation.splash_screen.SplashScreen
import com.rafael.tictactoe.settings.presentation.settings_screen.SettingsScreen

@Composable
@ExperimentalMaterial3Api
fun NavGraph() {
    val navController = rememberNavController()
    val enterTransition = fadeIn(
        animationSpec = tween(durationMillis = 300)
    )
    val exitTransition = fadeOut(
        animationSpec = tween(durationMillis = 300)
    )
    
    NavHost(
        navController = navController,
        startDestination = Destinations.Splash.screen,
        enterTransition = { enterTransition },
        exitTransition = { exitTransition }
     ) {
        composable(Destinations.Splash.screen) {
            SplashScreen(navController)
        }
        composable(Destinations.Main.screen) {
            MainScreen(navController)
        }
        composable(
            route = Destinations.Game.screen + "/{isCPUMode}",
            arguments = listOf(
                navArgument("isCPUMode") {
                    type = NavType.BoolType
                    defaultValue = false
                    nullable = false
                }
            )
        ) { backStackEntry ->
            GameScreen(
                navController,
                backStackEntry.arguments?.getBoolean("isCPUMode") ?: false
            )
        }
        composable(Destinations.Settings.screen) {
            SettingsScreen(navController)
        }
    }
}
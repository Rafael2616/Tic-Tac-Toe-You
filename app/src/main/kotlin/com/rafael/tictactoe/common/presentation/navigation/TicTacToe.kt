package com.rafael.tictactoe.common.presentation.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.navArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.game.presentation.game_screen.GameScreen
import com.rafael.tictactoe.game.presentation.main_screen.MainScreen
import com.rafael.tictactoe.settings.presentation.settings_screen.SettingsScreen

@Composable
@ExperimentalMaterial3Api
fun TicTacToe(context: ComponentActivity) {
    val navController = rememberNavController()
    val enterTransition = fadeIn(
        animationSpec = tween(
            durationMillis = 280,
            easing = EaseIn
        )
    )
    val exitTransition = fadeOut(
        animationSpec = tween(
            durationMillis = 280,
            easing = EaseOut
        )
    )

    AppTheme(context) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = Destinations.Main.screen,
                enterTransition = { enterTransition },
                exitTransition = { exitTransition }
             ) {
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
    }
}
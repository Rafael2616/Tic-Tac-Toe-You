package com.rafael.tictactoe.common.presentation.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.game.gameScreen.presentation.GameScreen
import com.rafael.tictactoe.game.mainScreen.presentation.MainScreen
import com.rafael.tictactoe.settings.libraryScreen.presentation.LibraryScreen
import com.rafael.tictactoe.settings.searchSettingsScreen.presentation.SearchSettingsScreen
import com.rafael.tictactoe.settings.settingsScreen.presentation.SettingsScreen

@Composable
@ExperimentalMaterial3Api
fun TicTacToeNavGraph(
    context: ComponentActivity,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val enterTransition = fadeIn(
        animationSpec = tween(
            durationMillis = 280,
            easing = EaseIn,
        ),
    )
    val exitTransition = fadeOut(
        animationSpec = tween(
            durationMillis = 280,
            easing = EaseOut,
        ),
    )

    AppTheme(context) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = modifier.fillMaxSize(),
        ) {
            NavHost(
                navController = navController,
                startDestination = Destinations.Main.screen,
                enterTransition = { enterTransition },
                exitTransition = { exitTransition },
            ) {
                composable(Destinations.Main.screen) {
                    MainScreen(
                        navController = navController,
                        activity = context,
                    )
                }
                composable(Destinations.Game.screen) {
                    GameScreen(navController)
                }
                composable(Destinations.Settings.screen) {
                    SettingsScreen(navController)
                }
                composable(Destinations.Library.screen) {
                    LibraryScreen(navController)
                }
                composable(Destinations.SearchSettings.screen) {
                    SearchSettingsScreen(navController)
                }
            }
        }
    }
}

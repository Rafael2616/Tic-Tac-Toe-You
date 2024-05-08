package com.rafael.tictactoe.core.navigation

import android.content.Intent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.game.gameScreen.presentation.GameScreen
import com.rafael.tictactoe.game.mainScreen.presentation.MainScreen
import com.rafael.tictactoe.settings.changelogsScreen.presentation.ChangelogsScreen
import com.rafael.tictactoe.settings.libraryScreen.presentation.LibraryScreen
import com.rafael.tictactoe.feature.settingssearch.presentation.SearchSettingsScreen
import com.rafael.tictactoe.settings.settingsScreen.presentation.SettingsScreen
import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut
import soup.compose.material.motion.animation.rememberSlideDistance

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalTextApi
fun TicTacToeNavGraph(
    modifier: Modifier = Modifier,
    intent: Intent? = null,
) {
    val navController = rememberNavController()
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val slideDistance = rememberSlideDistance()

    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = modifier.fillMaxSize(),
        ) {
            NavHost(
                navController = navController,
                startDestination = Destinations.Main.screen,
                enterTransition = { materialSharedAxisXIn(!isRtl, slideDistance) },
                exitTransition = { materialSharedAxisXOut(!isRtl, slideDistance) },
                popEnterTransition = { materialSharedAxisXIn(isRtl, slideDistance) },
                popExitTransition = { materialSharedAxisXOut(isRtl, slideDistance) },

            ) {
                composable(Destinations.Main.screen) {
                    MainScreen(
                        navController = navController,
                        intent = intent,
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
                composable(Destinations.Changelogs.screen) {
                    ChangelogsScreen(navController)
                }
            }
        }
    }
}

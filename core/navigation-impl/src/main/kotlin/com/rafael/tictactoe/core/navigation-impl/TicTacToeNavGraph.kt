package com.rafael.tictactoe.core.navigation.impl

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
import com.rafael.tictactoe.core.navigation.destinations.Changelogs
import com.rafael.tictactoe.core.navigation.destinations.Game
import com.rafael.tictactoe.core.navigation.destinations.LibraryInfo
import com.rafael.tictactoe.core.navigation.destinations.Main
import com.rafael.tictactoe.core.navigation.destinations.Settings
import com.rafael.tictactoe.core.navigation.destinations.SettingsSearch
import com.rafael.tictactoe.core.ui.theme.AppTheme
import com.rafael.tictactoe.feature.changelogs.presentation.ChangelogsScreen
import com.rafael.tictactoe.feature.game.presentation.GameScreen
import com.rafael.tictactoe.feature.libraryinfo.presentation.LibraryScreen
import com.rafael.tictactoe.feature.main.presentation.MainScreen
import com.rafael.tictactoe.feature.settings.presentation.SettingsScreen
import com.rafael.tictactoe.feature.settingssearch.presentation.SearchSettingsScreen
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
                startDestination = Main,
                enterTransition = { materialSharedAxisXIn(!isRtl, slideDistance) },
                exitTransition = { materialSharedAxisXOut(!isRtl, slideDistance) },
                popEnterTransition = { materialSharedAxisXIn(isRtl, slideDistance) },
                popExitTransition = { materialSharedAxisXOut(isRtl, slideDistance) },

            ) {
                composable<Main> {
                    MainScreen(
                        navController = navController,
                        intent = intent,
                    )
                }
                composable<Game> {
                    val args = it.toRoute<Game>()
                    GameScreen(navController, args.gameMode)
                }
                composable<Settings> {
                    SettingsScreen(navController)
                }
                composable<LibraryInfo> {
                    LibraryScreen(navController)
                }
                composable<SettingsSearch> {
                    SearchSettingsScreen(navController)
                }
                composable<Changelogs> {
                    ChangelogsScreen(navController)
                }
            }
        }
    }
}

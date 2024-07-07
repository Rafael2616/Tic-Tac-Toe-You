package com.rafael.tictactoe.core.navigation.impl

import android.content.Intent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rafael.tictactoe.core.navigation.destinations.Changelogs
import com.rafael.tictactoe.core.navigation.destinations.Game
import com.rafael.tictactoe.core.navigation.destinations.LibraryInfo
import com.rafael.tictactoe.core.navigation.destinations.Main
import com.rafael.tictactoe.core.navigation.destinations.Settings
import com.rafael.tictactoe.core.navigation.destinations.SettingsSearch
import com.rafael.tictactoe.core.ui.theme.AppTheme
import com.rafael.tictactoe.feature.changelogs.presentation.ChangelogsScreen
import com.rafael.tictactoe.feature.game.presentation.GameScreen
import com.rafael.tictactoe.feature.game.presentation.viewmodel.GameViewModel
import com.rafael.tictactoe.feature.libraryinfo.presentation.LibraryScreen
import com.rafael.tictactoe.feature.libraryinfo.presentation.viewmodel.LibraryViewModel
import com.rafael.tictactoe.feature.main.presentation.MainScreen
import com.rafael.tictactoe.feature.main.presentation.viewmodel.MainViewModel
import com.rafael.tictactoe.feature.settings.presentation.SettingsScreen
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import com.rafael.tictactoe.feature.settingssearch.presentation.SearchSettingsScreen
import com.rafael.tictactoe.feature.settingssearch.presentation.viewmodel.SearchSettingsViewModel
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
    settingsViewModel: SettingsViewModel = hiltViewModel(),) {
    val navController = rememberNavController()
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val slideDistance = rememberSlideDistance()
    val settingsState = settingsViewModel.state.collectAsStateWithLifecycle()
    val onSettingsEvent = settingsViewModel::onEvent

    AppTheme(
        state = settingsState.value,
        onEvent = onSettingsEvent,
    ) {
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
                    val mainViewModel = hiltViewModel<MainViewModel>()
                    MainScreen(
                        navController = navController,
                        intent = intent,
                        settingsVM = settingsViewModel,
                        mainVM = mainViewModel,
                    )
                }
                composable<Game> {
                    val args = it.toRoute<Game>()
                    val gameViewModel = hiltViewModel<GameViewModel>()
                    GameScreen(navController, args.gameMode, viewModel = gameViewModel, settingsVM = settingsViewModel)
                }
                composable<Settings> {
                    SettingsScreen(navController, settingsVM = settingsViewModel)
                }
                composable<LibraryInfo> {
                    val libraryViewModel = hiltViewModel<LibraryViewModel>()
                    LibraryScreen(navController, settingsVM = libraryViewModel)
                }
                composable<SettingsSearch> {
                    val searchSettingsViewModel = hiltViewModel<SearchSettingsViewModel>()
                    val searchState = searchSettingsViewModel.state.collectAsStateWithLifecycle()
                    val onSearchEvent = searchSettingsViewModel::onEvent
                    SearchSettingsScreen(
                        navController = navController,
                        searchState = searchState.value,
                        settingsState = settingsState.value,
                        onSearchEvent = onSearchEvent,
                        onEvent = onSettingsEvent,
                    )
                }
                composable<Changelogs> {
                    ChangelogsScreen(
                        navController = navController,
                        settingsState = settingsState.value,
                        onEvent = onSettingsEvent,
                    )
                }
            }
        }
    }
}

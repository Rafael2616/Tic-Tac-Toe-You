package com.rafael.tictactoe.core.navigation.impl

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil3.annotation.ExperimentalCoilApi
import com.rafael.tictactoe.core.navigation.Changelogs
import com.rafael.tictactoe.core.navigation.Destinations
import com.rafael.tictactoe.core.navigation.Game
import com.rafael.tictactoe.core.navigation.LibraryInfo
import com.rafael.tictactoe.core.navigation.Main
import com.rafael.tictactoe.core.navigation.Settings
import com.rafael.tictactoe.core.navigation.SettingsSearch
import com.rafael.tictactoe.core.platform.lifecycle.LifecycleEventsHandler
import com.rafael.tictactoe.core.platform.network.SingletonCoilImageLoader
import com.rafael.tictactoe.core.platform.permissions.PermissionsViewModel
import com.rafael.tictactoe.core.ui.theme.AppTheme
import com.rafael.tictactoe.feature.changelogs.presentation.ChangelogsScreen
import com.rafael.tictactoe.feature.game.presentation.GameScreen
import com.rafael.tictactoe.feature.game.presentation.viewmodel.GameViewModel
import com.rafael.tictactoe.feature.libraryinfo.presentation.LibraryScreen
import com.rafael.tictactoe.feature.libraryinfo.presentation.viewmodel.LibraryViewModel
import com.rafael.tictactoe.feature.main.presentation.MainScreen
import com.rafael.tictactoe.feature.main.presentation.viewmodel.MainViewModel
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.presentation.SettingsScreen
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import com.rafael.tictactoe.feature.settingssearch.presentation.SearchSettingsScreen
import com.rafael.tictactoe.feature.settingssearch.presentation.viewmodel.SearchSettingsViewModel
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut
import soup.compose.material.motion.animation.rememberSlideDistance

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalTextApi
@ExperimentalCoilApi
@KoinExperimentalAPI
fun TicTacToeNavGraph(
    modifier: Modifier = Modifier,
    destination: Destinations? = null,
) {
    val navController = rememberNavController()
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val slideDistance = rememberSlideDistance()
    val factory = rememberPermissionsControllerFactory()
    val permissionsController = factory.createPermissionsController()
    val permissionsViewModel =
        koinViewModel<PermissionsViewModel>(parameters = { parametersOf(permissionsController) })
    val permissionsState by permissionsViewModel.permissionsState.collectAsStateWithLifecycle()

    BindEffect(permissionsController)

    KoinContext {
        val settingsViewModel = koinViewModel<SettingsViewModel>()
        val settingsState by settingsViewModel.state.collectAsStateWithLifecycle()
        val onSettingsEvent = settingsViewModel::onEvent

        AppTheme(
            state = settingsState,
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
                        val mainViewModel = koinViewModel<MainViewModel>()
                        val mainState by mainViewModel.state.collectAsStateWithLifecycle()
                        MainScreen(
                            navController = navController,
                            destination = destination,
                            state = settingsState,
                            mainState = mainState,
                            onSettingsEvent = onSettingsEvent,
                            onRedirectToGameModes = mainViewModel::canRedirectToAnGameModeScreen,
                        )
                    }
                    composable<Game> {
                        val args = it.toRoute<Game>()
                        val gameViewModel = koinViewModel<GameViewModel>()
                        val gameState by gameViewModel.state.collectAsStateWithLifecycle()
                        val onGameEvent = gameViewModel::onEvent
                        GameScreen(
                            navController = navController,
                            gameMode = args.gameMode,
                            gameState = gameState,
                            settingsState = settingsState,
                            onGameEvent = onGameEvent,
                            onSettingsEvent = onSettingsEvent,
                            onUpdateSettingsState = gameViewModel::updateSettingsState,
                        )
                    }
                    composable<Settings> {
                        SettingsScreen(
                            navController = navController,
                            state = settingsState,
                            permissionsState = permissionsState,
                            requestGalleryPermission = permissionsViewModel::provideOrRequestGalleryPermission,
                            onEvent = onSettingsEvent,
                            onDeleteImage = settingsViewModel::onDeleteImage,
                            onSaveImage = settingsViewModel::onSaveImage,
                        )
                    }
                    composable<LibraryInfo> {
                        val libraryViewModel = koinViewModel<LibraryViewModel>()
                        val libraries by libraryViewModel.ossLibraries.collectAsStateWithLifecycle()
                        LibraryScreen(
                            navController = navController,
                            libraries = libraries,
                        )
                    }
                    composable<SettingsSearch> {
                        val searchSettingsViewModel = koinViewModel<SearchSettingsViewModel>()
                        val searchState by searchSettingsViewModel.state.collectAsStateWithLifecycle()
                        val onSearchEvent = searchSettingsViewModel::onEvent
                        SearchSettingsScreen(
                            navController = navController,
                            searchState = searchState,
                            settingsState = settingsState,
                            onSearchEvent = onSearchEvent,
                            onEvent = onSettingsEvent,
                        )
                    }
                    composable<Changelogs> {
                        ChangelogsScreen(
                            navController = navController,
                            settingsState = settingsState,
                            onEvent = onSettingsEvent,
                        )
                    }
                }
            }
        }
        SingletonCoilImageLoader()

        LifecycleEventsHandler.Handle(
            onStop = { onSettingsEvent(SettingsEvent.StartBackgroundMusic(isAppInOnStop = true)) },
            onResume = { onSettingsEvent(SettingsEvent.StartBackgroundMusic(isAppInOnStop = false)) },
        )
    }
}

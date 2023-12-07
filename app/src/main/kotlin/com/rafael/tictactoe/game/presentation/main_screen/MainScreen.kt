package com.rafael.tictactoe.game.presentation.mainScreen

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.game.presentation.gameScreen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.mainScreen.components.MainOptions
import com.rafael.tictactoe.game.presentation.mainScreen.components.MainTopBar
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.presentation.settingsScreen.viewmodels.SettingsViewModel

@Composable
fun MainScreen(
    navController: NavController,
    activity: ComponentActivity,
) {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val viewModel = hiltViewModel<GameViewModel>()
    val context = LocalContext.current
    val serviceIntent = Intent(context, MusicService::class.java)

    MainTopBar()
    MainOptions(
        navController,
        settingsVM,
        viewModel,
    )

    BackHandler {
        settingsVM.onEvent(SettingsEvent.IsBGMusicPlaying(false))
        activity.finish()
        context.stopService(serviceIntent)
    }
}

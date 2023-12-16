package com.rafael.tictactoe.game.presentation.mainScreen

import android.Manifest
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.game.presentation.gameScreen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.mainScreen.components.MainOptions
import com.rafael.tictactoe.game.presentation.mainScreen.components.MainTopBar
import com.rafael.tictactoe.game.presentation.mainScreen.components.WhatsNewDialog
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.presentation.settingsScreen.viewmodels.SettingsViewModel

@Composable
fun MainScreen(
    navController: NavController,
    activity: ComponentActivity,
    viewModel: GameViewModel = hiltViewModel(),
    settingsVM: SettingsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val serviceIntent = Intent(context, MusicService::class.java)
    val state by settingsVM.state.collectAsState()
    var showStoragePermissinRequest = false

    MainTopBar()
    MainOptions(
        navController,
        state,
        viewModel::onEvent,
    )

    AnimatedVisibility(state.showWhatsNewDialog) {
        WhatsNewDialog(
            settingsVM::onEvent,
        )
    }

    // This permission should not be shown to the end user, it is only for test
    // cases where there is a crash in the application in the release version in which
    // the developer needs the log file that is generated in the internal storage
    if (showStoragePermissinRequest) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            0,
        )
    }

    BackHandler {
        settingsVM.onEvent(SettingsEvent.IsBGMusicPlaying(false))
        activity.finish()
        context.stopService(serviceIntent)
    }
}

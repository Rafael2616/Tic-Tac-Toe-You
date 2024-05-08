package com.rafael.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.common.domain.util.AppExceptionHandler
import com.rafael.tictactoe.core.navigation.TicTacToeNavGraph
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.common.shortcuts.GameShortcutsCreator
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class, ExperimentalTextApi::class)
class TicTacToeActivity : ComponentActivity() {

    @Inject
    lateinit var settingsVM: SettingsViewModel

    @Inject
    lateinit var exoPlayer: ExoPlayer

    @Inject
    lateinit var musicService: MusicService

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        AppExceptionHandler.init()
        GameShortcutsCreator().create(this)

        setContent {
            TicTacToeNavGraph(intent = intent)
        }
    }

    override fun onResume() {
        settingsVM.onEvent(SettingsEvent.StartBackgroundMusic(false))
        super.onResume()
    }

    override fun onStop() {
        settingsVM.onEvent(SettingsEvent.StartBackgroundMusic(true))
        super.onStop()
    }

    override fun onDestroy() {
        exoPlayer.release()
        super.onDestroy()
    }
}

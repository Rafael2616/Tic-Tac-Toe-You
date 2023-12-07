package com.rafael.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.common.presentation.navigation.TicTacToe
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.presentation.settingsScreen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class TicTacToeActivity : ComponentActivity() {

    @Inject lateinit var settingsVM: SettingsViewModel

    @Inject lateinit var exoPlayer: ExoPlayer

    @Inject lateinit var musicService: MusicService

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            // Navigation graph
            TicTacToe(this)
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

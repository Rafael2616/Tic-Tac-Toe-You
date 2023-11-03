package com.rafael.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.common.presentation.navigation.TicTacToe
import com.rafael.tictactoe.game.domain.audio.BackgroundMusic
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class TicTacToeActivity : ComponentActivity() {

    @Inject lateinit var settingsVM: SettingsViewModel
    @Inject lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            enableEdgeToEdge()
            
            //Navigation graph
            TicTacToe(this)
        }
    }

    override fun onStop() {
        BackgroundMusic(
            context = this,
            onEvent = settingsVM::onEvent,
            state = settingsVM.state.value,
            isAppInOnStop = true
        )
        super.onStop()
    }
    
    override fun onResume() {
        BackgroundMusic(
            context = this,
            onEvent = settingsVM::onEvent,
            state = settingsVM.state.value,
            isAppInOnStop = false
        )
        super.onResume()
    }

    override fun onDestroy() {
        exoPlayer.release()
        super.onDestroy()
    }
}
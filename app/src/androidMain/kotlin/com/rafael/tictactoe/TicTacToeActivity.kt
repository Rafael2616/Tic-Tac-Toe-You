package com.rafael.tictactoe

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.core.navigation.Destinations
import com.rafael.tictactoe.core.navigation.Game
import com.rafael.tictactoe.core.navigation.impl.TicTacToeNavGraph
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.exception.AppExceptionHandler
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import com.rafael.tictactoe.shortcuts.GameShortcutsCreator
import org.koin.android.ext.android.inject

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class, ExperimentalTextApi::class)
class TicTacToeActivity : ComponentActivity() {

    private val settingsVM: SettingsViewModel by inject()
    private val exoPlayer: ExoPlayer by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        AppExceptionHandler.init()
        GameShortcutsCreator().create(this)

        val destination = handleIntent(intent)
        setContent {
            TicTacToeNavGraph(destination = destination)
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

    fun handleIntent(intent: Intent): Destinations? {
        if (intent.getStringExtra(getString(R.string.play)) == "s2") {
            return Game("PvP")
        }
        if (intent.getStringExtra(getString(R.string.vs_cpu)) == "s1") {
            return Game("VsCPU")
        }
        if (intent.getStringExtra(getString(R.string.online_match)) == "s0") {
            return Game("Online")
        }
        return null
    }
}
